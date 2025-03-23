<%-- 
    Document   : admindashboard
    Created on : Mar 22, 2025, 1:12:35 PM
    Author     : Acer Predator
--%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
        <link rel="stylesheet" href="css/admin.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />

    </head>
    <body>
        <div class="container">
            <aside>
                <div class="top">
                    <div class="logo">
                        <h2>C<span class="danger">ABC</h2>
                    </div>
                    <div class="close">
                        <span class="material-symbols-outlined"> close </span>
                    </div>
                </div>

                <div class="sidebar">
                    <a href="admindashboard.jsp" class="active">
                        <span class="material-symbols-outlined"> grid_view </span>
                        <h3>Dashboard</h3>
                    </a>

                    <a href="account"> 
                        <span class="material-symbols-outlined"> person </span>
                        <h3>Account</h3>
                    </a>

                    <a href="#">
                        <span class="material-symbols-outlined"> movie </span>
                        <h3>Movie</h3>
                    </a>

                    <a href="#">
                        <span class="material-symbols-outlined"> other_houses </span>
                        <h3>Theater</h3>
                    </a>

                    <a href="#">
                        <span class="material-symbols-outlined"> meeting_room </span>
                        <h3>Screening room</h3>
                    </a>

                    <a href="combo">
                        <span class="material-symbols-outlined"> attach_money </span>
                        <h3>Combo</h3>
                    </a>

                    <a href="#">
                        <span class="material-symbols-outlined"> app_promo </span>
                        <h3>Promotion</h3>
                    </a>

                    <a href="#">
                        <span class="material-symbols-outlined"> query_stats </span>
                        <h3>Revenue</h3>
                    </a>

                    <a href="#">
                        <span class="material-symbols-outlined"> logout </span>
                        <h3>Log out</h3>
                    </a>
                </div>
            </aside>

            <main>
                <h1>Dashboard</h1>
                <div class="date">
                    <input type="date">
                </div>

                <div class="insights">
                    <div class="sales">
                        <span class="material-symbols-outlined"> trending_up </span>
                        <div class="middle">
                            <div class="left">
                                <h3>Total sales</h3>
                                <h1>$25,023</h1>
                            </div>
                            <div class="progress">
                                <svg>
                                <circle r="30" cy="40" cx="40"></circle>
                                </svg>
                                <div class="number">80%</div>
                            </div>
                        </div>
                        <small>Last 24 hours</small>
                    </div>

                    <div class="expenses">
                        <span class="material-symbols-outlined"> local_mall </span>
                        <div class="middle">
                            <div class="left">
                                <h3>Expenses</h3>
                                <h1>$25,023</h1>
                            </div>
                            <div class="progress">
                                <svg>
                                <circle r="30" cy="40" cx="40"></circle>
                                </svg>
                                <div class="number">80%</div>
                            </div>
                        </div>
                        <small>Last 24 hours</small>
                    </div>

                    <div class="income">
                        <span class="material-symbols-outlined"> stacked_line_chart </span>
                        <div class="middle">
                            <div class="left">
                                <h3>Income</h3>
                                <h1>$25,023</h1>
                            </div>
                            <div class="progress">
                                <svg>
                                <circle r="30" cy="40" cx="40"></circle>
                                </svg>
                                <div class="number">80%</div>
                            </div>
                        </div>
                        <small>Last 24 hours</small>
                    </div>
                </div>
            </main>

            <div class ="right">
                <div class="top">
                    <button id="menu_bar">
                        <span class="material-symbols-outlined"> menu </span>
                    </button>
                    <div class="theme-toggler">
                        <span class="material-symbols-outlined active" > light_mode </span>
                        <span class="material-symbols-outlined"> dark_mode </span>
                    </div>
                    <div class="profile">
                        <div class="info">
                            <p><b>MMS</b></p>
                            <p>Admin</p>
                            <small class="text-muted"></small>
                        </div>
                        <span class="material-symbols-outlined"> account_circle </span>

                    </div>
                </div>
                <div class="recent_update">
                    <h2>Recent Updates</h2>
                    <div class="updates">
                        <div class="update">
                            <span class="material-symbols-outlined"> person </span>
                            <div class="message">
                                <p><b>UserA</b> has leave a comment</p>
                            </div>
                        </div>
                        <div class="update">
                            <span class="material-symbols-outlined"> person </span>
                            <div class="message">
                                <p><b>UserS</b> has leave a comment</p>
                            </div>
                        </div>
                        <div class="update">
                            <span class="material-symbols-outlined"> person </span>
                            <div class="message">
                                <p><b>UserB</b> has leave a comment</p>
                            </div>
                        </div>
                    </div>    
                </div>


                <div class="sales_analytics">
                    <h2>Sales Analytics</h2>
                    <div class="item">
                        <div class="icon">
                            <span class="material-symbols-outlined">shopping_cart</span>
                        </div>
                        <div class="right_text">
                            <div class="info">
                                <h3>online orders</h3>
                                <small class="text-muted">Last seen 2 hours</small>
                            </div>
                            <h5 class="danger">-17%</h5>
                            <h3>3849</h3>
                        </div>
                    </div>

                    <div class="item">
                        <div class="icon">
                            <span class="material-symbols-outlined">shopping_cart</span>
                        </div>
                        <div class="right_text">
                            <div class="info">
                                <h3>online orders</h3>
                                <small class="text-muted">Last seen 2 hours</small>
                            </div>
                            <h5 class="danger">17%</h5>
                            <h3>3849</h3>
                        </div>
                    </div>

                    <div class="item">
                        <div class="icon">
                            <span class="material-symbols-outlined">shopping_cart</span>
                        </div>
                        <div class="right_text">
                            <div class="info">
                                <h3>online orders</h3>
                                <small class="text-muted">Last seen 2 hours</small>
                            </div>
                            <h5 class="danger">17%</h5>
                            <h3>3849</h3>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>

