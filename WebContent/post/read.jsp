<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="row justify-content-center my-3 py-3">
	<h1 class="text-center">게시글정보</h1>
	<div class="col-md-6 my-3">		
		<div class="card">
			<div class="card-body">
				<h5>[${post.id}] ${post.title}</h5>
				<hr/>
				<p>${post.body}</p>
			</div>
			<div class="card-footer text-muted">
			 Posted on ${post.date} by ${post.writer}
			</div>
		</div>
		<h1 class="text-center">댓글목록</h1>
		<div class="card">
			<div class="card-body">
				<form class="text-center" name="frm">
					<textarea rows="5" class="form-control" name="body"></textarea>
					<button class="btn btn-primary my-2">등록</button>
				</form>
			</div>
		</div>
		<div id="comments"></div>
		<script id="temp" type="text/x-handlebars-template">
			{{#each list}}
				<div class="card mb-2">
					<div class="card-body">
						{{body}}
					</div>
					<div class="card-footer text-muted">
						 Posted on {{date}} by {{uname}}
					</div>
				</div>
			{{/each}}
		</script>
	</div>
	
</div>

<script>
	getComments();
	
	$(frm).on("submit",function(e){
		e.preventDefault();
		let body = $(frm.body).val();
		if(body==""){
			alert("댓글내용을 입력하세요!");
		}else{
			$.ajax({
				type:"post",
				url:"/comments/insert",
				data:{body:body,writer:"user02",postid:${post.id}},
				success:function(){
					$(frm.body).val("");
					getComments();
				}
			})
		}
	})
	
	function getComments(){
		$.ajax({
			type:"get",
			dataType:"json",
			url:"/comments.json",
			data:{postid:${post.id}},
			success:function(data){
				let temp = Handlebars.compile($("#temp").html());
				$("#comments").html(temp(data));
			}
		})
	}
</script>