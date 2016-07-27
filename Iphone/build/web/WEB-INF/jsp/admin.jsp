<%@page contentType="text/html" pageEncoding="UTF-8" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.css "/>">
                <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.css "/>">
                <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.min.css "/>">
                <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css "/>">

                <script type="text/javascript" src="/resources/bootstrap/js/jquery-1.11.3.min.js"></script>
                <script type="text/javascript" src="/resource/bootstrap/js/bootstrap.min.js"></script>
                <link rel="stylesheet" href="<c:url value="/resources/css/admin.css "/>">
                <title>JSP Page</title>
            </head>

            <body>
               
                    <div class="row" id="header">
                     
                        
                        <div class="row">
                            <div class="col-lg-10"> 
                                <img src="resources/assets/images/apple_admin.png" alt="Iphone">
                            </div>
                            iphone6@gmail.com<br>
                            <div class="col-lg-10"></div>
                            +375 29 255-88-88
                        </div>
                        
                       
                        
                        <div class="row">
                        <div class="col-lg-10"></div>
                            <form action="index.htm">
                                <input type="submit"  value="Выйти">
                            </form>
                        </div>
                    </div>

                    <div class="row" id="content">
                        <div class="table-responsive">
                            <div class="col-lg-12">
                                <table class="table table-striped table-bordered table-hover table-condensed">
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
                    </div>

                    <div class="row" id="buttons">
                        
                        <div class="col-lg-5">
                            <div class="row">
                                <form method="POST" action="uploadFile.htm" enctype="multipart/form-data">
                                    <input type="file" name="file"> <br>
                                    <input type="submit" value="Добавить телефон">
                                </form>
                            </div>
                            <div class="row">
                                <form method="POST" action="uploadFile.htm" enctype="multipart/form-data">
                                    <input type="file" name="file"> <br>
                                    <input type="submit" value="Обновить цену">
                                </form>
                            </div>
                        </div>
                        <div class="col-lg-2"></div>
                        <div class="col-lg-5">
                            <div class="row">
                                <div class="row">
                                    <form method="POST" action="uploadFile.htm" enctype="multipart/form-data">
                                        <input type="file" name="file"> <br>
                                        <input type="submit" value="Загрузить фотографию">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row" id="footer">
                        
                        <img src="resources/jpg/eftech.png" alt="Iphone"><br>
                        
                    </div>
             

            </body>

            </html>