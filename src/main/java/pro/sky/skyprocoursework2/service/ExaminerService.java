package pro.sky.skyprocoursework2.service;

import pro.sky.skyprocoursework2.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
