package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.repository.CodeRepository;
import platform.entity.CodeSnippet;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CodeServiceImpl implements CodeService {

    private final CodeRepository codeRepository;

    @Autowired
    public CodeServiceImpl(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public String addCode(CodeSnippet code) {
        codeRepository.save(code);
        return code.getId();
    }

    @Override
    public CodeSnippet getCode(String id) {
        Optional<CodeSnippet> codeOptional = codeRepository.findById(id);
        if (codeOptional.isPresent()) {
            return codeOptional.get();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "code not found");
        }
    }

    @Override
    public void updateViewById(CodeSnippet updateCode) {
        int views = updateCode.getViews();
        if (views > 0) {
            views--;
            updateCode.setViews(views);
            codeRepository.save(updateCode);
        } else {
            codeRepository.delete(updateCode);
        }
    }

    @Override
    public void updateTimeById(CodeSnippet updateCode) {
        LocalDateTime creationTime=updateCode.getCreationTime();
        LocalDateTime currentTime=LocalDateTime.now();
        Duration duration = Duration.between(creationTime,currentTime);
        int newTime= (int) (updateCode.getTime()-duration.toSeconds());
        if (newTime > 0) {
            updateCode.setTime(newTime);
            codeRepository.save(updateCode);
        } else {
            codeRepository.delete(updateCode);
        }
    }

    @Override
    public List<CodeSnippet> getLatestCodes() {
        List<CodeSnippet> latestCodes = codeRepository.findOrderByDateDescLimit10();
        return latestCodes;
    }

}