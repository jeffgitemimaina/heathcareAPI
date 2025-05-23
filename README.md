﻿## Deployment on Render with Aiven for MySQL
### Link to the working prototype and a powerpoint presentation [google drive](https://drive.google.com/drive/folders/1_NpFjaUNCHUUZNsjSmSv1ZpWJJf7i3Ok?usp=sharing)
1. **Set Up Aiven MySQL**:
   - Sign up at [Aiven Console](https://console.aiven.io).
   - Create a MySQL service `defaultdb` (Startup-1 Free).
   - Connection details:
      - Host: `healthdb-gitemijeff-c704.j.aivencloud.com`
      - Port: `17895`
2. **Configure Backend**:
   - Update `application.properties` with environment variables for `defaultdb`.
   - Set CORS for `https://healthcarefrontend-fwaf.onrender.com` in `HealthcareApiApplication.java`.
   - Add `Dockerfile` and configure `build.gradle` for JDK 21 with Lombok and Jakarta Validation.
3. **Push to GitHub**:
   - Commit and push to `https://github.com/jeffgitemimaina/healthcareAPI`.
4. **Deploy to Render**:
   - Create a Web Service in [Render](https://render.com).
   - Connect `healthcareAPI` repository.
   - Configure:
      - Environment: Docker
      - Build Command: Handled by `Dockerfile`
      - Start Command: Handled by `Dockerfile`
      - Environment Variables:
         - `DATABASE_URL`: `jdbc:mysql://healthdb-gitemijeff-c704.j.aivencloud.com:17895/defaultdb?ssl-mode=REQUIRED`
5. **Integrate with Frontend**:
   - Update frontend’s `BACKEND_API_URL` or `index.html` meta tag to `https://healthcare-api-backend.onrender.com/api`.
6. **Test**:
   - Test API endpoints with Postman.
   - Verify `defaultdb` in Aiven.
   - Test frontend at `https://healthcarefrontend-fwaf.onrender.com`.
