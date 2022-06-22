# Android MVVM Sample
For this project I have chosen MVVM architecture since it makes it easier to save the state after configuration
changes and I provided a base solution that will make it easier to add new ViewModels and extend the application with new screens.

I decided to use Coroutines and Flow for this project.

I used Hilt for dependency injection because I believe it has a smaller learning curve and comparing with Dagger 1 and Dagger 2.

I used Lottie for displaying the loading animation.

For next steps I am planning to implement the following:

- Unit tests for use case and view model
- Use Jetpack Compose
- Add a new screen and request data from an API with pagination 
- Improve solution to keep state after rotation change
  Right now we are not requesting again but we could retain the user query
