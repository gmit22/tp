package seedu.duke.parser;

import seedu.duke.command.project.CreateProjectCommand;
import seedu.duke.command.project.SelectProjectCommand;
import seedu.duke.command.project.ViewProjectCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectList;

import java.util.ArrayList;
import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.CREATE;
import static seedu.duke.command.CommandSummary.VIEW;
import static seedu.duke.command.CommandSummary.TITLE;
import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.DURATION;
import static seedu.duke.command.CommandSummary.SPRINT_DURATION;
import static seedu.duke.command.CommandSummary.SELECT;

public class ProjectParser implements ExceptionsParser {

    @Override
    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                ProjectList projectListManager)
            throws DukeException {
        switch (action.toLowerCase()) {
        case CREATE:
            if (!parameters.containsKey(TITLE) || !parameters.containsKey(DESCRIPTION)
                    || !parameters.containsKey(DURATION) || !parameters.containsKey(SPRINT_DURATION)) {
                throw new DukeException("Missing parameters.");
            }
            if (parameters.get(TITLE).isBlank()) {
                throw new DukeException("no title");
            }
            if (parameters.get(DESCRIPTION).isBlank()) {
                throw new DukeException("no description");
            }
            if (parameters.get(DURATION).isBlank() || !Parser.isStringContainsNumber(parameters.get(DURATION))) {
                throw new DukeException("please give a number for duration");
            }
            if (parameters.get(SPRINT_DURATION).isBlank()
                    || !Parser.isStringContainsNumber(parameters.get(SPRINT_DURATION))) {
                throw new DukeException("please give a number for sprint duration");
            } else {
                new CreateProjectCommand(parameters, projectListManager).execute();
            }
            break;
        case VIEW:
            new ViewProjectCommand(parameters, projectListManager).execute();
            break;
        case SELECT:
            new SelectProjectCommand(parameters, projectListManager).execute();
            break;
        default:
            throw new DukeException("Invalid action!");
        }
    }
}