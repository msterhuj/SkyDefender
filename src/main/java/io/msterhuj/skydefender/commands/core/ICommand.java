package io.msterhuj.skydefender.commands.core;

import io.msterhuj.skydefender.commands.core.annotations.Command;
import io.msterhuj.skydefender.commands.core.annotations.CommandArg;
import io.msterhuj.skydefender.commands.core.annotations.PlayerName;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public abstract class ICommand {

    protected List<ICommand> subCommands = new ArrayList<>();

    public boolean execute(CommandSender commandSender, org.bukkit.command.Command command, String alias, String[] args) {
        return help(commandSender);
    }

    public boolean help(CommandSender commandSender) {
        Command commandInfo = this.getClass().getAnnotation(Command.class);

        List<CommandArg> arguments = getCommandFields().stream().map(field -> field.getAnnotation(CommandArg.class)).collect(Collectors.toList());
        ArrayList<String> helper = new ArrayList<>();
        helper.add(commandInfo.name());
        helper.add(commandInfo.description());
        helper.addAll(arguments.stream().map(commandArg -> commandArg.name() + " : " + commandArg.description()).collect(Collectors.toList()));

        commandSender.sendMessage(helper.toArray(new String[0]));
        return true;
    }

    public List<String> getSubCommandsName() {
        return this.getSubCommands().stream().map(ICommand::getName).collect(Collectors.toList());
    }

    public String getName() {
        return getClass().getAnnotation(Command.class).name();
    }

    /**
     * Get the aliases of the current command
     *
     * @return String[]
     */
    public String[] getAliases() {
        return getClass().getAnnotation(Command.class).aliases();
    }

    /**
     * Check if an argument is an alias of the current command
     *
     * @param arg the alias to check without case
     * @return true if the argument is an alias
     */
    public boolean isAlias(String arg) {
        for (String alias : getAliases()) {
            if (alias.equalsIgnoreCase(arg)) {
                return true;
            }
        }
        return false;
    }

    public List<Field> getCommandFields() {
        return Arrays.stream(this.getClass().getFields())
                .filter(field -> field.isAnnotationPresent(CommandArg.class)).collect(Collectors.toList());
    }

    /**
     * Search for a sub command of the current command by it's name or alias
     *
     * @param aliasOrName the name or alias of the search command (case ignored)
     * @return Optional<ICommand>
     */
    private Optional<ICommand> getSubCommand(String aliasOrName) {
        return this.subCommands.stream().filter(subCommand ->
                aliasOrName.equalsIgnoreCase(subCommand.getName())
                        || subCommand.isAlias(aliasOrName)
        ).findFirst();
    }

    /**
     * Return the ICommand corresponding to the current command
     * The search is filtering by ICommand name, aliases and skip the parameters of the command
     *
     * @param currentCommand the current command
     * @return ICommand
     */
    public ICommand getCurrentCommand(String[] currentCommand) {
        //We filter the arguments considered as current command parameters
        String[] filteredParameters = Arrays.copyOfRange(currentCommand, Math.min(currentCommand.length, getCommandFields().size()), currentCommand.length);

        if (currentCommand.length == 0 || filteredParameters.length == 0)
            return this;

        else {
            Optional<ICommand> command = getSubCommand(filteredParameters[0]);

            return command.isPresent() ? command.get().getCurrentCommand(Arrays.copyOfRange(filteredParameters, 1, filteredParameters.length)) : this;
        }
    }

    public List<String> getCurrentArgumentSuggestions(String[] currentCommand) {
        List<String> suggestions = new ArrayList<>();
        Field currentArgument = getCurrentArgument(currentCommand);

        if (currentArgument == null) {
            return suggestions;
        }

        System.out.println(currentArgument.getName());

        if (currentArgument.getAnnotation(PlayerName.class) != null) {
            suggestions.addAll(Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()));
        }

        if (currentArgument.getType().isEnum()) {
            suggestions.addAll(Arrays.stream(currentArgument.getType().getEnumConstants()).map(Object::toString).collect(Collectors.toList()));
        }
        return suggestions;
    }

    public Field getCurrentArgument(String[] currentCommand) {
        List<Field> currentFields = getCommandFields();
        String[] filteredParameters = Arrays.copyOfRange(currentCommand, Math.min(currentCommand.length, currentFields.size()), currentCommand.length);

        if (filteredParameters.length > 0) {
            Optional<ICommand> command = getSubCommand(filteredParameters[0]);
            if (command.isPresent()) {
                return command.get().getCurrentArgument(Arrays.copyOfRange(filteredParameters, 1, filteredParameters.length));
            }
        }

        filteredParameters = Arrays.copyOfRange(currentCommand, 1, currentCommand.length);
        if (currentFields.isEmpty() || currentFields.size() < filteredParameters.length || filteredParameters.length == 0) {
            return null;
        }

        return currentFields.get(filteredParameters.length - 1);
    }


}
