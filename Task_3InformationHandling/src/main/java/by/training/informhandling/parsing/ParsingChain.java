package by.training.informhandling.parsing;

import by.training.informhandling.entity.TextTree;

import java.util.List;

public interface ParsingChain {
    List<TextTree> parseCurrentText();
}
