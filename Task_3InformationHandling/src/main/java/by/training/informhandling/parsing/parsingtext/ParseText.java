package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composit;

public abstract class ParseText {
    protected ParseText parser;
    abstract public Component parse(Composit curParser, String text);
}
