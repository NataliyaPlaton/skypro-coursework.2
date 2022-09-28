package pro.sky.skyprocoursework2.service;

import org.springframework.stereotype.Service;
import pro.sky.skyprocoursework2.exception.IncorrectAmountOfQuestion;
import pro.sky.skyprocoursework2.model.Question;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getAll().size() || amount <= 0) {
            throw new IncorrectAmountOfQuestion();
        }
        Set<Question> questions = new HashSet<>(amount);
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}
