package com.inhatc.system.board.vo;

import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker{
	
	private int totalCount; //all data count
	private int startPage; 
	private int endPage; 
	private boolean prev; //back
	private boolean next; //front
	
	private int displayPageNum = 10;
	
	private Criteria cri;
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calcData();
	}
	
	private void calcData() {
		
		endPage = (int) (Math.ceil(cri.getPage()/
				(double) displayPageNum) * displayPageNum);
		
		startPage = (endPage - displayPageNum) + 1; 
		
		
		int tempEndPage = (int) (Math.ceil(totalCount/
				(double) cri.getPerPageNum()));
		
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		} 
		
		prev = startPage == 1 ? false : true; 
		
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
		
	
	}

	public String makeQuery(int page) {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance()
			.queryParam("page", page)
			.queryParam("perPageNum", this.cri.getPerPageNum());
		//검색 한 경우		
		if (this.cri.getSearchType() != null) {
			uriComponentsBuilder
				.queryParam("searchType", this.cri.getSearchType())
				.queryParam("keyword", this.cri.getKeyword());
		}
		return uriComponentsBuilder.build().encode().toString();
	}
	
	
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getStartPage() {
		return startPage;
	}

	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}

	public Integer getEndPage() {
		return endPage;
	}

	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}
	
	
}