package kklazy.hrc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.hrc.model.Article;
import kklazy.persistence.service.impl.DefaultService;

/**
 * @author kk
 */
@Service("articleService")
@Transactional(rollbackFor = Exception.class)
public class ArticleService extends DefaultService<Article, String> {


}
