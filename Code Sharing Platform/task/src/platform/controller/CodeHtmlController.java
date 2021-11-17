package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.service.CodeService;
import platform.entity.CodeSnippet;

import java.util.List;

@Controller
public class CodeHtmlController {

    private CodeService codeService;

    @Autowired
    public CodeHtmlController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(path = "/code/{id}", produces = "text/html")
    public String getCode(@PathVariable("id") String id, Model model) {
        CodeSnippet codeSnippet = codeService.getCode(id);
        if (codeSnippet.isViewRestricted()) {
            codeService.updateViewById(codeSnippet);
        }
        if (codeSnippet.isTimeRestricted()) {
            codeService.updateTimeById(codeSnippet);
        }
        model.addAttribute("theCode", codeSnippet);
        return "codeView";
    }

    @GetMapping(path = "/code/latest", produces = "text/html")
    public String getLatestCodes(Model model) {
        List<CodeSnippet> lastCodes= codeService.getLatestCodes();
        model.addAttribute("latestCodes", lastCodes);
        return "codeList";
    }

    @GetMapping(path = "/code/new", produces = "text/html")
    public String createNewCode() {
        return "create";
    }

}

