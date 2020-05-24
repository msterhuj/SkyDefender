package io.msterhuj.skydefender.commands.core;

import io.msterhuj.skydefender.commands.core.annotations.Command;
import io.msterhuj.skydefender.commands.core.annotations.CommandArg;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public abstract class ICommand {

    private List<ICommand> subCommands;

    public void execute() {
        this.help();
    }

    public void help() {
        Command commandInfo = this.getClass().getAnnotation(Command.class);

        Field[] fields = this.getClass().getFields();
    }

    public List<String> getSubCommandsName() {
        return this.getSubCommands().stream().map(command -> getName()).collect(Collectors.toList());
    }

    public String getName() {
        return getClass().getAnnotation(Command.class).name();
    }

    /**
     * Get the aliases of the current commandF
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

    /**
     * Return the list of CommandArg annotation within the class
     *
     * @return List<CommandArg>
     */
    private List<CommandArg> getCommandArguments() {
        return Arrays.stream(this.getClass().getFields())
                .filter(field -> Arrays.asList(field.getAnnotations()).contains(CommandArg.class))
                .map(field -> field.getAnnotation(CommandArg.class))
                .collect(Collectors.toList());
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
                        || isAlias(aliasOrName)
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
        String[] filteredParameters = Arrays.copyOfRange(currentCommand, Math.min(currentCommand.length, getCommandArguments().size()), currentCommand.length);

        if (currentCommand.length == 0 || filteredParameters.length == 0)
            return this;

        else {
            Optional<ICommand> command = getSubCommand(filteredParameters[0]);

            return command.isPresent() ? command.get().getCurrentCommand(Arrays.copyOfRange(filteredParameters, 1, filteredParameters.length)) : this;
        }
    }
}
