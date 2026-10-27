package ro.uvt.info.SPLab_MacoveiRaul.command;

import org.springframework.stereotype.Component;

// Invoker-ul: stie doar sa execute o comanda, nu si ce face ea
@Component
public class CommandInvoker {

    public <T> T execute(Command<T> command) {
        return command.execute();
    }
}
