package io.msterhuj.skydefender.commands.core;

import io.msterhuj.skydefender.commands.core.annotations.Command;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
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

    public String[] getAliases() {
        return getClass().getAnnotation(Command.class).aliases();
    }

    public boolean isAlias(String arg) {
        for (String alias : getAliases()) {
            if(alias.equalsIgnoreCase(arg)) {
                return true;
            }
        }
        return false;
    }

    private ICommand getCurrentCommand(String[] args) {
        if(args.length == 0)
            return this;
        else {
             Optional<ICommand> command = this.subCommands.stream().filter(subCommand ->
                    args[0].equalsIgnoreCase(subCommand.getName())
                    || isAlias(args[0])
                     ).findFirst();
             return command.isPresent() ? command.get().getCurrentCommand(Arrays.copyOfRange(args,1, args.length)) : this;
        }
    }
}
