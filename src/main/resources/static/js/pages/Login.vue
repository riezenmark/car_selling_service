<template>
    <div>
        <form action="/login" method="post">
            <div>
                <label>Username: <input type="text" name="username"></label>
            </div>
            <div>
                <label>Password: <input type="password"></label>
            </div>
            <div>
                <input type="submit" value="Sign In">
            </div>
        </form>
        Don't have an account? <a href="/signup">Sign Up</a>
    </div>
</template>

<script>

</script>

<style>

</style>