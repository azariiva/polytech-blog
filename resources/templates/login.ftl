<#include "main.ftl">

<#macro page_body>
    <section class="vh-100 gradient-custom">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card bg-dark text-white" style="border-radius: 1rem;">
                        <div class="card-body p-5 text-center">

                            <div class="mb-md-5 mt-md-4 pb-5">

                                <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
                                <p class="text-white-50 mb-5">Please enter your login and password!</p>
                                <form action="/login" method="post">
                                    <div class="form-floating mb-4 text-black-50">
                                        <input type="text" id="inputLogin" class="form-control form-control-lg"
                                               placeholder="Login" name="username">
                                        <label for="inputLogin">Login</label>
                                    </div>

                                    <div class="form-floating mb-4 text-black-50">
                                        <input type="password" id="inputPassword" class="form-control form-control-lg"
                                               placeholder="Password" name="password">
                                        <label for="inputPassword">Password</label>
                                    </div>

                                    <#--                                <p class="small mb-5 pb-lg-2"><a class="text-white-50" href="#!">Forgot password?</a></p>-->

                                    <input class="btn btn-outline-light btn-lg px-5" type="submit" value="Login">
                                </form>
                            </div>

                            <div>
                                <p class="mb-0">
                                    Don't have an account? <a href="/signup" class="text-white-50 fw-bold">Sign Up</a>
                                </p>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</#macro>

<@main userIdPrincipalName=""/>