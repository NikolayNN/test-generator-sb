package my.nhorushko.testgeneratorsb.testgeneratorsb.shell;
import my.nhorushko.testgeneratorsb.testgeneratorsb.service.CmdService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AppCommands {

    private final CmdService cmdService;

    public AppCommands(CmdService cmdService) {
        this.cmdService = cmdService;
    }

    @ShellMethod("run app")
    public void run(
            @ShellOption(defaultValue = "en") String lang
    ) {
        cmdService.run(lang);
    }
}
