package platform.service;

import platform.entity.CodeSnippet;

import java.util.List;

public interface CodeService {
    String addCode(CodeSnippet code);
    CodeSnippet getCode(String id);
    void updateViewById(CodeSnippet codeSnippet);
    void updateTimeById(CodeSnippet codeSnippet);
    List<CodeSnippet> getLatestCodes();

}
