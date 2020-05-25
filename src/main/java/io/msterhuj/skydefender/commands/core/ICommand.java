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
    private Optional<ICommand> getSubCommand(String aliasOrName, boolean byAlias) {
        return this.subCommands.stream().filter(subCommand ->
                aliasOrName.equalsIgnoreCase(subCommand.getName())
                        || (byAlias && subCommand.isAlias(aliasOrName))
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
            Optional<ICommand> command = getSubCommand(filteredParameters[0], true);
            return command.isPresent() ? command.get().getCurrentCommand(Arrays.copyOfRange(filteredParameters, 1, filteredParameters.length)) : this;
        }
    }

    public List<String> getSuggestions(String[] currentCommand) {
        List<String> suggestions = new ArrayList<>();

        List<Field> fields = getCommandFields();
        String[] filteredParameters = Arrays.copyOfRange(currentCommand, Math.min(currentCommand.length, fields.size()), currentCommand.length);

        if (filteredParameters.length > 1 && !filteredParameters[0].isEmpty()) {
            Optional<ICommand> command = getSubCommand(filteredParameters[0], true);
            if (command.isPresent()) {
                suggestions.addAll(command.get().getSuggestions(Arrays.copyOfRange(filteredParameters, 1, filteredParameters.length)));
            }
        } else {
            if (fields.size() >= currentCommand.length && !fields.isEmpty()) {
                suggestions.addAll(getArgumentSuggestions(fields.get(currentCommand.length - 1)));
            } else {
                suggestions.addAll(getSubCommandsName());
            }
        }

        if (currentCommand.length > 0) {
            suggestions = suggestions.stream()
                    .filter(name -> (name.toLowerCase().startsWith(currentCommand[currentCommand.length - 1]) || name.contains("[<")))
                    .collect(Collectors.toList());
        }
        return suggestions;
    }

    public List<String> getArgumentSuggestions(Field argument) {
        List<String> suggestions = new ArrayList<>();
        if (argument != null) {
            if (argument.getAnnotation(PlayerName.class) != null) {
                suggestions.addAll(Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()));
            } else if (argument.getType().isEnum()) {
                suggestions.addAll(Arrays.stream(argument.getType().getEnumConstants()).map(Object::toString).collect(Collectors.toList()));
            } else {
                suggestions.add("[<" + argument.getName() + ">]");
            }
        }
        return suggestions;
    }

}
