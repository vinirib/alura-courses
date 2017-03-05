<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Livros de Java, SOA, Android, iPhone, Ruby on Rails e muito mais">
	
	<jsp:attribute name="extraScripts">
		<script> console.log("Finalização de compra de ${carrinhoCompras.quantidade} itens"); </script>
	</jsp:attribute>


	<jsp:body>
		<section class="container middle">
			  <h2 id="cart-title">Seu carrinho de compras</h2>
			  
			  
			    <table id="cart-table">
			      <colgroup>
			        <col class="item-col"/>
			        <col class="item-price-col"/>
			        <col class="item-quantity-col"/>
			        <col class="line-price-col"/>
			        <col class="delete-col"/>
			      </colgroup>
			      <thead>
			        <tr>
			          <th class="cart-img-col" width="10%">Imagem</th>
			          <th width="55%">Item</th>
			          <th width="10%">Preço</th>
			          <th width="10%">Quantidade</th>
			          <th width="10%">Total</th>
			          <th width="5%"></th>
			        </tr>
			      </thead>
			      <tbody>
				      <c:forEach items="${carrinhoCompras.itens }" var="item">
					    <tr>
					        <td class="cart-img-col" style="padding-left:20px">
        			   			<c:if test="${item.produto.imageFile == '' }">
									<c:if test="${item.produto.sumarioPath == null }">
										<img src="https://cdn.shopify.com/s/files/1/0155/7645/products/java8-featured_large.png?v=1411490181" width="71px" height="100px" />
									</c:if>
									<img src="${item.produto.sumarioPath}" width="71px" height="100px" />
								</c:if>
								<c:if test="${item.produto.imageFile != '' }">
						            <img src="data:image/jpg;base64,${item.produto.imageFile}" width="71px" height="100px" />
								</c:if>
					        </td>
					        <td class="item-title">${item.produto.titulo}</td>
					        <td class="numeric-cell">${item.preco}</td>
					        <td class="quantity-input-cell">
					            <input type="number" min="0" readonly="readonly" id="quantidade" name="quantidade" value="${carrinhoCompras.getQuantidade(item) }" />
					        </td>
					        <td class="numeric-cell">${carrinhoCompras.getTotal(item)}</td>
							<td class="remove-item">
							    <form:form servletRelativeAction="/carrinho/remover" method="post">
							        <input type="hidden" name="produtoId" value="${item.produto.id}">
							        <input type="hidden" name="tipoPreco" value="${item.tipoPreco}">
							        <input type="image" src="/excluir.png" alt="Excluir" title="Excluir" />
							    </form:form>
							</td>			    
						</tr>
					</c:forEach>
			      </tbody>
					<tfoot>
					    <tr>
					        <td colspan="3">
							<form:form servletRelativeAction="/produtos/finalizar" method="post">
					            <input type="submit" class="checkout" name="checkout" value="Finalizar compra" />
	    					  </form:form>
					        </td>
					        <td class="numeric-cell">${carrinhoCompras.total}</td>
					        <td></td>
					    </tr>
					</tfoot>
			    </table>
			  
			  <h2>Você já conhece os outros livros da Casa do Código?</h2>
			  <ul id="collection" class="related-books">          
					<c:forEach items="${produtos}" var="produto">
					      <li class="col-left">
					      <c:if test="${produto.imageFile == '' }">
								<c:if test="${produto.sumarioPath == null }">
							        <a href="${s:mvcUrl('PC#detalhe').arg(0, produto.id).build()}" class="block clearfix book-suggest" data-book="${produto.titulo}">
							          <img width="113px" height="160px" src="http:////cdn.shopify.com/s/files/1/0155/7645/products/plsql-featured_compact.png?v=1434740236" alt="${produto.titulo}"/>
							        </a>
								</c:if>
									<a href="${s:mvcUrl('PC#detalhe').arg(0, produto.id).build()}" class="block clearfix book-suggest" data-book="${produto.titulo}">
							          <img width="113px" height="160px" src="${produto.sumarioPath}" alt="${produto.titulo}"/>
							        </a>
						   </c:if>
						   <c:if test="${produto.imageFile != '' }">
						        <a href="${s:mvcUrl('PC#detalhe').arg(0, produto.id).build()}" class="block clearfix book-suggest" data-book="${produto.titulo}">
						          <img width="113px" height="160px" src="data:image/jpg;base64,${produto.imageFile}" alt="${produto.titulo}"/>
						        </a>
					        </c:if>
					      </li>          
			      	</c:forEach>
			  </ul>
			  
			  <h2><a href="${s:mvcUrl('PC#listar').build() }">Retornar para lista de produtos</a></h2>
			</section> 
			
	</jsp:body>
</tags:pageTemplate>