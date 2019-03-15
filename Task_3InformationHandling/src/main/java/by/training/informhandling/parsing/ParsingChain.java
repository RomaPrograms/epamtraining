package by.training.informhandling.parsing;

import by.training.informhandling.entity.PrintTree;

import java.util.List;

public interface ParsingChain {
    List<PrintTree> parseCurrentText();
}
