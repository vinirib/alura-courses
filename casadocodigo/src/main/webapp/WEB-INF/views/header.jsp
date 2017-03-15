<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
							<li>
								<a href="${s:mvcUrl('CCC#itens').build()}" rel="nofollow">
							        <fmt:message key="menu.carrinho">
            							<fmt:param value="${carrinhoCompras.quantidade}"/>
       								 </fmt:message>
     							</a>
     						</li>
     						 <li>
					            <a href="?locale=pt" rel="nofollow">
					                <fmt:message key="menu.pt"/>
					            </a>
					        </li>
					        <li>
					            <a href="?locale=en_US" rel="nofollow">
					                <fmt:message key="menu.en"/>
					            </a>
					        </li>
						</ul>
					</nav>
				</div>
			</div>
		</header>
		<nav class="categories-nav">
			<ul class="container">
				<li class="category"><a href="/casadocodigo"><fmt:message key="navegacao.categoria.home"/></a></li>
				<li class="category"><a href="${s:mvcUrl('HC#collection').arg(0, 'AGILE').build()}">
						<fmt:message key="navegacao.categoria.agile"/></a></li>
				<li class="category"><a href="${s:mvcUrl('HC#collection').arg(0, 'FRONT_END').build()}">
						<fmt:message key="navegacao.categoria.front_end"/></a></li>
				<li class="category"><a href="${s:mvcUrl('HC#collection').arg(0, 'GAMES').build()}">
						<fmt:message key="navegacao.categoria.games"/> </a></li>
				<li class="category"><a href="${s:mvcUrl('HC#collection').arg(0, 'JAVA').build()}">
						<fmt:message key="navegacao.categoria.java"/> </a></li>
				<li class="category"><a href="${s:mvcUrl('HC#collection').arg(0, 'MOBILE').build()}">
						<fmt:message key="navegacao.categoria.mobile"/> </a></li>
				<li class="category"><a
					href="${s:mvcUrl('HC#collection').arg(0, 'WEB').build()}"> <fmt:message key="navegacao.categoria.web"/> </a></li>
				<li class="category"><a href="${s:mvcUrl('HC#collection').arg(0, 'OUTROS').build()}"> <fmt:message key="navegacao.categoria.outros"/> </a></li>
			</ul>
		</nav>