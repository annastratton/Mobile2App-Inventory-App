# Mobile2App-Inventory-App
CS-360-X6155 Mobile Architect &amp; Programming 23EW6


Architecture
Frontend Development Comparison
In this full stack project, I utilized different types of frontend development:

Express with HTML & JavaScript: This approach offers the simplicity of server-side rendering. The server processes the data and sends a fully rendered HTML page to the browser. This method is straightforward, but it may not offer the seamless user interaction that modern web applications strive for.

Single-page application (SPA): SPAs, like the one I developed using Angular, load most of the necessary content once and then dynamically display data without needing to reload the entire page. The user experience is more fluid, closely resembling native applications.

While both methods have their merits, SPAs offer more interactive and responsive user experiences, which was essential for the modern feel of this project.

NoSQL MongoDB Database
The backend opted for a NoSQL MongoDB database for several reasons:

Schema Flexibility: Unlike relational databases, MongoDB is schema-less, allowing for easy modifications and a more adaptable data structure.
Scalability: MongoDB scales horizontally using sharding, which is an essential feature for potential high-traffic applications.
JSON-like documents: The data format is intuitive and aligns well with JavaScript objects, simplifying data interchange between server and client.
Functionality
JSON vs. JavaScript
JSON (JavaScript Object Notation) is a lightweight data-interchange format. While it originated from JavaScript, it's distinct in that it's only a string representation of data. JavaScript, on the other hand, is a full-fledged programming language. JSON acts as a bridge in full stack development, providing a standard way for the frontend and backend to communicate and exchange data.

Code Refactoring & UI Components
During the development process:

I spotted redundant code patterns, especially in the frontend. To optimize, I abstracted them into reusable UI components. This not only reduced the codebase's size but also enhanced consistency across the application.

Benefits of reusable UI components include faster development (since components can be reused), consistent UI/UX, and easier maintenance.

Testing
API Testing and Security
In a full-stack environment:

Methods refer to the types of requests you can send to the server (e.g., GET, POST, PUT, DELETE). Each method serves a specific purpose in CRUD operations.

Endpoints are specific URLs where requests are directed and determine which part of the application the server will access or modify.

With added security layers, such as authentication tokens, testing becomes more challenging. Tools like Postman or Insomnia can be used to mimic authenticated requests by including tokens in the headers.

It's vital to ensure that all endpoints are secure, returning data only to authenticated and authorized users, and that they handle data accurately and efficiently.

Reflection
This course has been instrumental in furthering my professional journey. The comprehensive approach, starting from frontend development nuances to backend intricacies, provided a holistic view of full-stack development.

I've honed skills such as:

Developing SPAs with modern frameworks like Angular.
Backend development with Node.js and Express.
Database operations with MongoDB.
Efficient testing methodologies for a secure full-stack application.
These competencies undoubtedly enhance my marketability in the tech domain, ensuring I'm equipped to take on diverse roles in full-stack development and contribute effectively to any team.
