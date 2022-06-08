package app.prasan.spacexwiki

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}