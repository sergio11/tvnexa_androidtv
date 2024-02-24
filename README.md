# TvNexa - Your Global IPTV Destination 🌐📺

<img width="auto" height="200px" align="left" src="doc/main_logo.png" />

Welcome to TvNexa, your ultimate online television platform that redefines the streaming experience. TvNexa is crafted with innovation, bringing you a diverse universe of television channels from around the world. Whether you're using Android TV or your mobile device, TvNexa is designed for seamless exploration and enjoyment of global content.

<p align="center">
  <img src="https://img.shields.io/badge/Android%20Studio-3DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white" />
  <img src="https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white" />
  <img src="https://img.shields.io/badge/Material%20UI-007FFF?style=for-the-badge&logo=mui&logoColor=white" />
  
</p>

## Overview 🌐

🌐 **TvNexa: Bringing the World to Your Screen! 📺** TvNexa is an innovative online television platform developed for Android TV using Jetpack Compose for TV and Jetpack Material 3. The project also includes a mobile version for a consistent viewing experience across devices.

## User-Focused Features 🚀

- **User Profiles**: Create multiple profiles on your account for personalized experiences.
- **Parental Controls**: Apply parental control measures, including channel blocking, NSFW content blocking, and time restrictions.
- **Favorite Channels Management**: Easily manage and organize your favorite channels.
- **Search Functionality**: Search for specific channels with ease.
- **Intuitive Navigation**: Enjoy a simple and intuitive navigation experience.
- **Channel Categorization**: Channels are classified by categories and countries for easy browsing.
- **Electronic Program Guide (EPG)**: Access channel schedules and program information through the EPG.

## Technical Details 🛠️

### Clean Architecture

TvNexa follows the principles of Clean Architecture, ensuring a clear separation of concerns and maintainability. The project is organized into layers – Presentation, Domain, and Data – to facilitate scalability and testability.

### Networking

Efficient communication with backend services is achieved through Retrofit and OkHttp.

### Video Playback

For a high-quality streaming experience, TvNexa leverages ExoPlayer. This powerful media player is integrated into the project to handle the playback of HLS IPTV content.

### Jetpack Compose for TV

The user interface is built using Jetpack Compose for TV, providing a modern and declarative UI toolkit for building native Android applications.


### MVI Architecture with Clean Architecture using Jetpack Compose

TvNexa follows a robust architectural approach combining MVI (Model-View-Intent) with Clean Architecture, empowered by Jetpack Compose. This architecture ensures a clear separation of concerns, maintainability, and scalability, making TvNexa a reliable and flexible platform for streaming content.

#### MVI Architecture:
MVI architecture revolves around three main components:

1. **Model**: Represents the data state of the application. Each screen has a unified state model observed with a StateFlow, ensuring consistency and predictability across the UI. Additionally, Side Effects events are utilized to manage asynchronous tasks and other events that may not have a direct representation in the UI, ensuring smooth operation of the application.

   
2. **View**: Renders the user interface and receives user input.
   
3. **Intent**: Represents user actions or events that trigger state changes.

By adopting the MVI pattern, TvNexa achieves a unidirectional data flow, enhancing predictability and testability while minimizing side effects.

#### Clean Architecture:
TvNexa adheres to Clean Architecture principles, which include:

1. **Presentation Layer**: Responsible for rendering the UI and handling user interactions. Jetpack Compose facilitates the implementation of the presentation layer, enabling the creation of a modern and declarative UI.
   
2. **Domain Layer**: Contains the business logic and use cases of the application. This layer remains independent of any frameworks or platforms, promoting reusability and testability.
   
3. **Data Layer**: Manages data retrieval and manipulation, including interactions with external services such as backend APIs. Retrofit and OkHttp facilitate efficient communication with backend services.

#### Integration with Jetpack Compose:
Jetpack Compose is utilized throughout TvNexa to build the user interface, providing a seamless and intuitive experience for users. Compose's declarative approach simplifies UI development and enables rapid iteration, allowing TvNexa to adapt to evolving user needs efficiently.

#### Benefits:
- **Scalability** 📈: The combination of MVI and Clean Architecture allows TvNexa to scale smoothly as the application grows in complexity and functionality.
  
- **Maintainability** 🛠️: Clear separation of concerns facilitates easier maintenance and updates, ensuring TvNexa remains robust and reliable over time.
  
- **Flexibility** 🎨: Jetpack Compose's flexibility and adaptability empower TvNexa to deliver a tailored and immersive streaming experience to users across different devices and platforms.

By embracing MVI architecture with Clean Architecture and leveraging Jetpack Compose, TvNexa sets a high standard for online television platforms, prioritizing performance, maintainability, and user satisfaction.

## App Screenshots

### Onboarding

Here are some screenshots from our app to give you a glimpse of its design and functionality.

<p align="center">
  <img width="700px" src="doc/picture_2.png" />
</p>

<p align="center">
  <img width="700px" src="doc/picture_3.png" />
</p>

<p align="center">
  <img width="700px" src="doc/picture_1.png" />
</p>

### Sign In

<p align="center">
  <img width="700px" src="doc/picture_10.png" />
</p>

<p align="center">
  <img width="700px" src="doc/picture_11.png" />
</p>

<p align="center">
  <img width="700px" src="doc/picture_12.png" />
</p>

<p align="center">
  <img width="700px" src="doc/picture_13.png" />
</p>

### Sign Up

<p align="center">
  <img width="700px" src="doc/picture_14.png" />
</p>

### Profiles

<p align="center">
  <img width="700px" src="doc/picture_15.png" />
</p>

<p align="center">
  <img width="700px" src="doc/picture_16.png" />
</p>

<p align="center">
  <img width="700px" src="doc/picture_17.png" />
</p>

<p align="center">
  <img width="700px" src="doc/picture_18.png" />
</p>

<p align="center">
  <img width="700px" src="doc/picture_19.png" />
</p>

<p align="center">
  <img width="700px" src="doc/picture_20.png" />
</p>

<p align="center">
  <img width="700px" src="doc/picture_21.png" />
</p>

<p align="center">
  <img width="700px" src="doc/picture_22.png" />
</p>

### Settings

<p align="center">
  <img width="700px" src="doc/picture_4.png" />
</p>

<p align="center">
  <img width="700px" src="doc/picture_5.png" />
</p>

<p align="center">
  <img width="700px" src="doc/picture_6.png" />
</p>

<p align="center">
  <img width="700px" src="doc/picture_7.png" />
</p>

<p align="center">
  <img width="700px" src="doc/picture_8.png" />
</p>

