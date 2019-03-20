package by.training.informhandling.entity;

import java.util.List;

public interface Component extends Cloneable{
    List<Component> getComponents();
    void setIsOutputText(boolean isOutputText);
    void add(Component c, Category category);
    void remove(Component c);
    Object getChild (int index);
    Component clone();
}
