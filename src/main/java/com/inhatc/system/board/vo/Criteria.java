package com.inhatc.system.board.vo;

import org.springframework.web.util.UriComponentsBuilder;

public class Criteria { //Criteria:검색기준, 분류기준
	//굳이 안만들어두 댐. 파라미터 2개 이상 받도록 설계하면 되는데, 검색과 같은 기능이 붙게되면 점점 전달되는 파라미터양이 많아지므로 관리하기 불편
	
	private Integer page;
	private Integer perPageNum;
	private String manager_belong;
	private String writer;
	
	//속성 searchType, keyword 추가
	private String searchType;
	private String keyword;

	public Criteria() {
		this.page = 1; //기본 값 1페이지
		this.perPageNum = 10; //리스트 당 데이터의 수는 10으로 강제 부여
	}

	public void setPage(int page) {

		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}

	public void setPerPageNum(int perPageNum) {

		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}

		this.perPageNum = perPageNum;
	}
 // set메소드로 사용자가 고의로 잘못 입력할 수 있는 값에 대해 필요한 데이터를 조정가능
	
	public int getPage() {
		return page;
	}

	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}

	public int getPerPageNum() { //한 페이지당 보여지는 개수
		return this.perPageNum;
	}
	
	public String makeQuery() {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", this.perPageNum);
				
		if (searchType!=null) {
			uriComponentsBuilder
					.queryParam("searchType", this.searchType)
					.queryParam("keyword", this.keyword);
		}
		return uriComponentsBuilder.build().encode().toString();
	}
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", searchType=" + searchType + ", keyword="
				+ keyword + "]";
	}

	

	public String getManager_belong() {
		return manager_belong;
	}

	public void setManager_belong(String manager_belong) {
		this.manager_belong = manager_belong;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setPerPageNum(Integer perPageNum) {
		this.perPageNum = perPageNum;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

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
