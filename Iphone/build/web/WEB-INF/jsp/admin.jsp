<%@page contentType="text/html" pageEncoding="UTF-8" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" href="<c:url value="/resources/bootstrap-3.3.5-dist/css/bootstrap.css "/>">
                <link rel="stylesheet" href="<c:url value="/resources/bootstrap-3.3.5-dist/css/bootstrap-theme.css "/>">
                <link rel="stylesheet" href="<c:url value="/resources/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css "/>">
                <link rel="stylesheet" href="<c:url value="/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css "/>">

                <script type="text/javascript" src="/resources/bootstrap/js/jquery-1.11.3.min.js"></script>
                <script type="text/javascript" src="/resource/bootstrap/js/bootstrap.min.js"></script>
                <link rel="stylesheet" href="<c:url value="/resources/css/admin.css "/>">
                <title>JSP Page</title>
            </head>

            <body>
                <div class="container">
                    <div class="row" id="header">
                        <div class="span12">header</div>
                    </div>

                    <div class="row" id="content">
                        
                        <div class="col-lg-12">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>id</th>
                                        <th>model</th>
                                        <th>manufacturer</th>
                                        <th>year</th>
                                        <th>type</th>
                                        <th>os</th>
                                        <th>display_size</th>
                                        <th>display_resolution</th>
                                        <th>ram_memory</th>
                                        <th>flash_memory</th>
                                        <th>sim_several</th>
                                        <th>camera</th>
                                        <th>matrix_points</th>
                                        <th>design_case</th>
                                        <th>material</th>
                                        <th>color</th>
                                        <th>sim_format</th>
                                        <th>dimensions</th>
                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="o" items="${objects}">
                                        <tr>
                                            <td>${o.id}</td>
                                            <td>${o.model}</td>
                                            <td>${o.manufacturer}</td>
                                            <td>${o.year}</td>
                                            <td>${o.type}</td>
                                            <td>${o.os}</td>
                                            <td>${o.display_size}</td>
                                            <td>${o.display_resolution}</td>
                                            <td>${o.ram_memory}</td>
                                            <td>${o.flash_memory}</td>
                                            <td>${o.sim_several}</td>
                                            <td>${o.camera}</td>
                                            <td>${o.matrix_points}</td>
                                            <td>${o.design_case}</td>
                                            <td>${o.material}</td>
                                            <td>${o.color}</td>
                                            <td>${o.sim_format}</td>
                                            <td>${o.dimensions}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                        </div>
                    </div>

                    <div class="row" id="buttons">
                        
                        <div class="col-lg-5">
                            <div class="row">
                                <input type="button" class="btn" value="Найти файл"/>
                            </div>
                            <div class="row">
                                <input type="button" class="btn" value="Добавить телефон"/>
                            </div>
                            <div class="row">
                                <input type="button" class="btn" value="Найти файл"/>
                            </div>
                            <div class="row">
                                <input type="button" class="btn" value="Обновить цену"/>
                            </div>


                        </div>
                        <div class="col-lg-2"></div>
                        <div class="col-lg-5">
                            <div class="row">
                                <form action="find.htm">
                                    <input type="submit"  value="Найти файл">
                                
                                </form>
                            </div>
                            <div class="row">
                                <input type="button" class="btn" value="Загрузить фотографию"/>
                            </div>
                        </div>
                    </div>

                    <div class="row" id="footer">
                        <div class="span12">footer </div>
                    </div>
                </div>

            </body>

            </html>