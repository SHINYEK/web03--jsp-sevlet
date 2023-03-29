<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <div class="row justify-content-center my-5 mx-2">
 	<div class="col-md-8">
 		<h1>게시글</h1>
 		<div>전체 게시글수:<span id="total"></span> </div>
 		
 		<div id="posts"></div>
 		
 		<%--템플릿 안에 테이블이 들어와야 부트스트랩이 적용된다.--%>
		<script id="temp" type="text/x-handlebars-template">
			<table class="table table-striped">
			{{#each list}}
				<tr>
					<td>{{id}}</td>
					<td><div class="ellipsis"><a href="/posts/read?id={{id}}">{{title}}</a></div></td>
					<td>{{writer}}</td>
					<td>{{date}}</td>
				</tr> 
			{{/each}}
			</table>
		</script>
		
		
		<hr/>
			<div class="container">
		        <nav aria-label="Page navigation">
		            <ul class="paginationv justify-content-center" id="pagination"></ul>
		        </nav>
	    	</div>
 	</div>
 </div>
<script>
	getPosts(1);
	
    function updatePaging(total) {
    	let totalCount = total;
    	let pageSize = 10;
    	let pageNumber = 1;
    	
        let totalPages = totalCount / pageSize;
        if (totalCount % pageSize > 0){
        	totalPages++;
        }
        
        $('#pagination').twbsPagination({ 
            startPage: 1,
            totalPages: totalPages,
            visiblePages: 5,
            first: '<span sris-hidden="true">«</span>',
            last: '<span sris-hidden="true">»</span>',
            prev: "&lt;",
            next: "&gt;",
            onPageClick: function(event, page) {
                getPosts(page);
            }
        });
    }
    
    function getPosts(page) {
        $.ajax({
            type: "get",
            url: "/posts/json",
            dataType: "json",
            data: { page: page },
            success: function(data) {
            	 updatePaging(data.total)
                $("#total").html(data.total);
                let temp = Handlebars.compile($("#temp").html());
                $("#posts").html(temp(data));
            }
        })
    }
    
    getPosts(1);
</script>
