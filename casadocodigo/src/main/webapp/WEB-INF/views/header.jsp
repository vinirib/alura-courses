<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

		<header id="layout-header">
			<div class="clearfix container">
				<a href="/casadocodigo" id="logo"> </a>
				<div id="header-content">
					<nav id="main-nav">
						<ul class="clearfix">
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<li><a href="/casadocodigo/logout" rel="nofollow">Logout</a></li>
								<li><a href="${s:mvcUrl('PC#listar').build()}"
									rel="nofollow">Listagem de Produtos</a></li>
								<li><a href="${s:mvcUrl('PC#form').build()}" rel="nofollow">Cadastro
										de Produtos</a></li>
							</security:authorize>
							<security:authorize access="!isAuthenticated()">
								<li><a href="/casadocodigo/login" rel="nofollow">Login</a></li>
							</security:authorize>
							<li><a href="${s:mvcUrl('CCC#itens').build()}" rel="nofollow">Carrinho
									( ${carrinhoCompras.quantidade} )</a></li>
							<li><a href="/pages/sobre-a-casa-do-codigo" rel="nofollow">Sobre
									Nós</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</header>
		<nav class="categories-nav">
			<ul class="container">
				<li class="category"><a href="/casadocodigo">Home</a></li>
				<li class="category"><a href="${s:mvcUrl('HC#collection').arg(0, 'AGILE').build()}">
						Agile </a></li>
				<li class="category"><a href="${s:mvcUrl('HC#collection').arg(0, 'FRONT_END').build()}">
						Front End </a></li>
				<li class="category"><a href="${s:mvcUrl('HC#collection').arg(0, 'GAMES').build()}">
						Games </a></li>
				<li class="category"><a href="${s:mvcUrl('HC#collection').arg(0, 'JAVA').build()}">
						Java </a></li>
				<li class="category"><a href="${s:mvcUrl('HC#collection').arg(0, 'MOBILE').build()}">
						Mobile </a></li>
				<li class="category"><a
					href="${s:mvcUrl('HC#collection').arg(0, 'WEB').build()}"> Web </a></li>
				<li class="category"><a href="${s:mvcUrl('HC#collection').arg(0, 'OUTROS').build()}"> Outros </a></li>
			</ul>
		</nav>