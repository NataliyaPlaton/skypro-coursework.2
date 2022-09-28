package pro.sky.skyprocoursework2.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pro.sky.skyprocoursework2.exception.QuestionAlreadyExistException;
import pro.sky.skyprocoursework2.exception.QuestionNotFoundException;
import pro.sky.skyprocoursework2.model.Question;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


class JavaQuestionServiceTest {
    private final QuestionService questionService = new JavaQuestionService();

    @AfterEach
    public void afterEach() {
        questionService.getAll().forEach(questionService::remove);
    }

    @Test
    public void addTest() {
        assertThat(questionService.getAll()).isEmpty();
        Question expected = add(new Question("q1", "a1"));

        questionService.add("q2", "a2");

        assertThat(questionService.getAll()).hasSize(2);
        assertThat(questionService.getAll()).contains(expected, new Question("q2", "a2"));

        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> questionService.add(expected));

        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> questionService.add("q2", "a2"));

    }

    @Test
    public void removeTest() {
        assertThat(questionService.getAll()).isEmpty();

        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("test", "test")));

        Question expected = add(new Question("q1", "a1"));
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("test", "test")));


        questionService.remove(expected);

        assertThat(questionService.getAll().isEmpty());

    }

    @Test
    public void getRandomQuestionTest() {
        for (int i = 1; i <= 5; i++) {
            add(new Question("q" + i, "a" + i));
        }
        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());
    }

    private Question add(Question question) {
        int sizeBefore = questionService.getAll().size();

        questionService.add(question);

        assertThat(questionService.getAll()).hasSize(sizeBefore + 1);
        assertThat(questionService.getAll()).contains(question);

        return question;

    }
}