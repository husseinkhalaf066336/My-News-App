# My-News-App
## About:
The goal is to create a News app which gives a user regularly-updated news from the internet related to a particular topic, or location,
This app uses NewsAPI to get various sources and each source can provide major headlines. It uses Retrofit 2 to fetch news sources and news headlines from the API and displays in a RecyclerView. It uses Room Persistence Library to provide offline 
## Libraries and Frameworks :
- [retrofi 2](https://square.github.io/retrofit/)
- [RxJava 3](https://github.com/ReactiveX/RxJava)
- [Room](https://developer.android.com/jetpack/androidx/releases/room) - Local Database
- [Hilt - Dagger](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency Injection
- [Material Design](https://material.io/design) - Design System
- [Splash Screen](https://developer.android.com/reference/android/window/SplashScreen)
- [Lottie](https://github.com/airbnb/lottie-android) - Animation
- [Google News API] (https://newsapi.org/s/google-news-api)
- [navigation-drawer](https://material.io/components/navigation-drawer)
- [intuit](https://github.com/intuit/sdp)
- [View Binding](https://developer.android.com/topic/libraries/view-binding)
- [TabLayout](https://developer.android.com/reference/com/google/android/material/tabs/TabLayout)
- [viewpager2](https://developer.android.com/guide/navigation/navigation-swipe-view-2)
- [DeffUtil with  RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView)
- [Supporting Swipe to Refresh](https://developer.android.com/training/swipe)
- [WebView](https://developer.android.com/reference/android/webkit/WebView)
- [SearchView](https://developer.android.com/reference/android/widget/SearchView)
- 
- ## Architecture:
- The app uses [MVVM](https://developer.android.com/topic/libraries/architecture/viewmodel) [Model-View-ViewModel] architecture to have a unidirectional flow of data, separation of concern, testability, and a lot more.
- [Guide to app architecture](https://proandroiddev.com/building-modern-apps-using-the-android-architecture-guidelines-3238fff96f14)
![mvvm](https://user-images.githubusercontent.com/88562339/140253495-0ae0eb51-8b74-4e70-80f8-2ab47b628ae8.png)
