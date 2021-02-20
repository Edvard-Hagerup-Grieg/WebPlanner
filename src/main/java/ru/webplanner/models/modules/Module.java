package ru.webplanner.models.modules;

public class Module {
    protected ModuleType type;
    protected int id;
    protected int sectionId;

    public Module(ModuleType type) {
        this.type = type;
    }

    public ModuleType getType() {
        return type;
    }

    public void setType(ModuleType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }
}
