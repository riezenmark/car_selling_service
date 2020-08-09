package org.example.carsellingservice.domain.view;

public final class Views {
    public interface Id { }
    public interface Name { }
    public interface Email { }
    public interface IdName extends Id, Name { }
    public interface IdNameEmail extends IdName, Email { }
}
