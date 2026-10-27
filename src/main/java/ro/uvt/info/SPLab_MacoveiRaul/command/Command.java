package ro.uvt.info.SPLab_MacoveiRaul.command;

// o operatie impachetata ca obiect; T e tipul rezultatului
public interface Command<T> {
    T execute();
}
