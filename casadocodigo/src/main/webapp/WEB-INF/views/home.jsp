<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/head.jsp" %>
	<nav class="categories-nav">
		<ul class="container">
			<li class="category"><a href="http://www.casadocodigo.com.br">Home</a></li>
			<li class="category"><a href="/collections/livros-de-agile">
					Agile </a></li>
			<li class="category"><a href="/collections/livros-de-front-end">
					Front End </a></li>
			<li class="category"><a href="/collections/livros-de-games">
					Games </a></li>
			<li class="category"><a href="/collections/livros-de-java">
					Java </a></li>
			<li class="category"><a href="/collections/livros-de-mobile">
					Mobile </a></li>
			<li class="category"><a
				href="/collections/livros-desenvolvimento-web"> Web </a></li>
			<li class="category"><a href="/collections/outros"> Outros </a></li>
		</ul>
	</nav>

			
	<section id="index-section" class="container middle">

		<h1 class="cdc-call">Últimos dias com os preços promocionais. Aproveite!</h1>
		<ul class="clearfix book-collection">

			<c:forEach items="${produtos}" var="produto">
				<li><a href="${s:mvcUrl('PC#detalhe').arg(0, produto.id).build()}" class="block clearfix">
					<h2 class="product-title">${produto.titulo}</h2>
					<c:if test="${produto.imageFile == '' }">
						<img width="143"
							height="202"
							src="https://cdn.shopify.com/s/files/1/0155/7645/products/java8-featured_large.png?v=1411490181"
							alt="${produto.titulo}"
							title="${produto.titulo}"/>
					</c:if>
						<img width="143"
							height="202"
							src="data:image/jpg;base64,${produto.imageFile}"
							alt="${produto.titulo}"
							title="${produto.titulo}"/>
						<small class="buy-button">Compre</small>
				</a></li>
			</c:forEach>
			
		</ul>

		<h2 class="cdc-call">Diferenciais da Casa do Código</h2>

		<ul id="cdc-diferenciais" class="clearfix">
			<li class="col-left">
				<h3>E-books sem DRM. Leia onde quiser</h3>
				<p>
					<span class="sprite" id="sprite-drm"></span> Nossos e-books não
					possuem DRM, ou seja, você pode ler em qualquer computador, tablet
					e smartphone.
				</p>
			</li>
			<li class="col-right">
				<h3>Autores de renome na comunidade</h3>
				<p>
					<span class="sprite" id="sprite-renome"></span> Autores que
					participam ativamente na comunidade com Open Source, listas de
					discussão, grupos e mais.
				</p>
			</li>
			<li class="col-left">
				<h3>Receba atualizações dos e-books</h3>
				<p>
					<span class="sprite" id="sprite-atualizacoes"></span> Quando você
					compra um e-book, automaticamente tem direito às atualizações e
					correções dele.
				</p>
			</li>
			<li class="col-right">
				<h3>Livros com curadoria da Caelum</h3>
				<p>
					<span class="sprite" id="sprite-caelum"></span> Desenvolvedores
					experientes que avaliam e revisam os livros constantemente.
				</p>
			</li>
		</ul>



	</section>

<%@include file="/WEB-INF/views/footer.jsp" %>	

