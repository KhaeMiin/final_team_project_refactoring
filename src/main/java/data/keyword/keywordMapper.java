package data.keyword;

import java.util.List;


public interface keywordMapper {
	public List<KeywordDTO> getKeywordList();
	public void insertKeyword(String keyword);
}
