package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Test;

import static ExpenseService.Expense.ExpenseType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        Project project=new Project(ProjectType.INTERNAL,"project");
        // when
        ExpenseType expenseCodeByProjectTypeAndName = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        assertEquals(expenseCodeByProjectTypeAndName,INTERNAL_PROJECT_EXPENSE);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        Project project=new Project(ProjectType.EXTERNAL,"Project A");
        // when
        ExpenseType expenseCodeByProjectTypeAndName = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        assertEquals(expenseCodeByProjectTypeAndName,EXPENSE_TYPE_A);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        Project project=new Project(ProjectType.EXTERNAL,"Project B");
        // when
        ExpenseType expenseCodeByProjectTypeAndName = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        assertEquals(expenseCodeByProjectTypeAndName,EXPENSE_TYPE_B);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        Project project=new Project(ProjectType.EXTERNAL,"Project C");
        // when
        ExpenseType expenseCodeByProjectTypeAndName = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        assertEquals(expenseCodeByProjectTypeAndName,OTHER_EXPENSE);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        // given
        Project project=new Project(ProjectType.UNEXPECTED_PROJECT_TYPE,"Project ABC");
        // when
        // then
        assertThrows(UnexpectedProjectTypeException.class,
                ()->{ExpenseService.getExpenseCodeByProjectTypeAndName(project);});
    }
}