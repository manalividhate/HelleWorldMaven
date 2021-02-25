<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>

<body>

    <div class="container">
        <form action="login" method="POST">
            <div class="row">
                <div class="col-3"></div>
                <div class="col-6">
                </div>
                <div class="col-3"></div>

            </div>
            <div class="row">
                <div class="col-3"></div>
                <div class="col-6">
                </div>
                <div class="col-3"></div>
            </div>
            <div class="mb-3 row">
                <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="" placeholder="Enter email" name="email" style="height:45px; width:500px">
                                    
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
                <div class="col-sm-10">
                <input type="password" class="form-control" id="inputPassword" name="password"  style="height:45px; width:500px">
                </div>

            </div>
            <div class="row">
                <div class="col-3"></div>

            <div class="col-6">
                <button type="submit" style="height: 45px; width: 200px" class="btn btn-primary">Login</button>

            </div>
            <div class="col-3"></div>
            </div>
            <div class="row">
                <div class="col-3"></div>
                <div class="col-6">

            <label for="text" class="col-sm-4 col-form-label">You dont have account<a href="registration.jsp"> register here...</a></label>
                </div>   
           <div class="col-3"></div>
          </div>
        </form>
    </div>
</body>
</html>