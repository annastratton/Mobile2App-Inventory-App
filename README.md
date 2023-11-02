# InventoryManagerApp
CS-360-X6155 Mobile Architect & Programming 23EW6

## Overview
InventoryManagerApp is a sophisticated solution for managing inventory, catering to personal collections and small business needs. It streamlines inventory tracking, editing, and management, providing an efficient and user-friendly platform.

## User Interface and Functionality
The app is designed with a user-centric approach, incorporating screens and features that enhance the user experience:

- **Inventory Dashboard**: Users can effortlessly view, modify, and append items in their inventory.
- **Item Details Screen**: This provides an in-depth view and editing options for individual inventory items.

The design ensures simplicity and effectiveness, with intuitive layouts, clear labels, and large touch targets for easy interaction. Navigation within the app is straightforward, adhering to best practices in UI/UX design to ensure user satisfaction and efficiency.

## Development Approach
The application is developed using an iterative and modular approach, leveraging the MVC (Model-View-Controller) design pattern to segregate the app's logic, user interface, and data handling. This ensures code maintainability and scalability. 

Peer programming and code reviews are integral to our development process, enhancing code quality and promoting collaborative learning.

## Testing
Extensive testing is a cornerstone of our development process. We commence with unit tests to validate individual components, followed by integration tests to ensure the app's overall integrity and functionality. This rigorous testing regime ensures the app is reliable and trustworthy, aligning with user expectations and needs.

## Challenges and Solutions
One of the challenges faced during development was optimizing the app's performance, especially when handling large datasets. Our solution involved implementing efficient data retrieval methods and incorporating lazy loading, ensuring a seamless user experience even with extensive inventories.

## Highlights and Expertise
The Inventory Dashboard is a highlight of the application, seamlessly integrating core functionalities while maintaining an intuitive user interface. This component showcases our expertise in mobile app development, emphasizing our commitment to delivering high-quality software.

## Conclusion
InventoryManagerApp stands as a robust inventory management solution, embodying industry best practices in design, development, and testing. We are committed to continually improving the app, taking user feedback and evolving requirements into account to enhance its capabilities and user experience. 

---

## Developer Guide

### Getting Started

1. Clone the repository:
```
git clone https://github.com/yourusername/InventoryManagerApp.git
```

2. Open the project in Android Studio.

3. Sync the project with Gradle files.

4. Run the app on an emulator or real device.

### Dependencies

This project relies on the following dependencies:

- Android SDK v30
- SQLite for Android for local data storage
- SMS permissions for sending notifications (optional)

Make sure all dependencies are installed and properly configured before running the app.

### Code Structure

- `MainActivity`: Handles user authentication, account creation, and SMS permission requests.
- `DatabaseHelper`: Manages database creation, connection, and queries.
- `SmsHandler`: Handles SMS permissions and sending SMS notifications (if permissions are granted).

Code is structured according to the MVC pattern, ensuring separation of concerns and easier maintainability.

### Testing

To run unit tests and integration tests, navigate to the `src/test` directory and run the tests using Android Studio's built-in test runner.

### Contributing

We welcome contributions to the project. Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Create a new Pull Request.

Ensure your code follows the project's coding conventions and has adequate test coverage.

---

© 2023 InventoryManagerApp Team

CS-360-X6155 Mobile Architect & Programming 23EW6
