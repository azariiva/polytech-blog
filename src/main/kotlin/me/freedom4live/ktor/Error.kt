package me.freedom4live.ktor

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
class UserAlreadyExistException : RuntimeException()
class UserNotFoundException : RuntimeException()
class ArticleNotFoundException : RuntimeException()