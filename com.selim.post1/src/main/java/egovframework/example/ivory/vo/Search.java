package egovframework.example.ivory.vo;

//search 클래스가 pagination 클래스를 상속 받았기 대문에 기존 pagination 특성을 그대로 사용할 수 있음
public class Search extends Pagination {
    
    private String searchType;
    private String keyword;
    
    public String getSearchType() {
        return searchType;
    }
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
}
