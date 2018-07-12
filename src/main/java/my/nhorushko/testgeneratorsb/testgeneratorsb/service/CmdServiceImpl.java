package my.nhorushko.testgeneratorsb.testgeneratorsb.service;

import my.nhorushko.testgeneratorsb.testgeneratorsb.controller.ApplicationController;
import org.springframework.stereotype.Service;

@Service
public class CmdServiceImpl implements CmdService {

    private ApplicationController applicationController;

    public CmdServiceImpl(ApplicationController applicationController) {
        this.applicationController = applicationController;
    }

    @Override
    public void run(String language) {
        applicationController.run(language);
    }
}
