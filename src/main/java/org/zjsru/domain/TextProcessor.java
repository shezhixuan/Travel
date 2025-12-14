package org.zjsru.domain;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TextProcessor {
    // 正面情感词
    private static final Set<String> POSITIVE_WORDS = Set.of(
            "好", "棒", "美", "舒适", "方便", "周到", "有趣", "丰富", "干净", "漂亮","齐全"
    );

    // 负面情感词
    private static final Set<String> NEGATIVE_WORDS = Set.of(
            "差", "糟糕", "脏", "不便", "无聊", "差劲", "拥挤", "吵闹", "贵", "不满意"
    );

    // 分词
    public static List<String> segment(String text) {
        List<Term> termList = StandardTokenizer.segment(text);
        return termList.stream()
                .map(term -> term.word)
                .collect(Collectors.toList());
    }
    // 提取关键词
    public static List<String> extractKeywords(String text, int topN) {
        return HanLP.extractKeyword(text, topN);
    }
    // 情感分析
    public static String sentimentAnalysis(String text) {
        List<String> words = segment(text);
        int positiveCount = 0;
        int negativeCount = 0;

        for (String word : words) {
            if (POSITIVE_WORDS.contains(word)) {
                positiveCount++;
            } else if (NEGATIVE_WORDS.contains(word)) {
                negativeCount++;
            }
        }
        if (positiveCount > negativeCount) {
            return "正面";
        } else if (negativeCount > positiveCount) {
            return "负面";
        } else {
            return "中性";
        }
    }
}
