FittBase

Projekt jest aplikacją na system Android, której głównym celem jest monitorowanie kondycji fizycznej i zdrowego trybu życia. Aplikacja składa się z kilku kluczowych modułów i funkcjonalności:

1. **Moduł Rejestracji i Logowania**:
   - `Register.java`: Klasa odpowiedzialna za proces rejestracji nowego użytkownika. Pobiera dane wejściowe, takie jak adres e-mail, nazwa użytkownika i hasło, a następnie waliduje je i przesyła do bazy danych.
   - `Login.java`: Klasa zarządzająca procesem logowania użytkownika. Pobiera dane logowania (adres e-mail i hasło) i weryfikuje je z danymi przechowywanymi w bazie danych.
   - `DataValidation.java`: Klasa pomocnicza do walidacji danych wejściowych, takich jak nazwa użytkownika, adres e-mail i hasło.

2. **Weryfikacja Adresu E-mail**:
   - `Verification.java`: Fragment odpowiedzialny za proces weryfikacji adresu e-mail użytkownika. Po zarejestrowaniu się, użytkownik otrzymuje kod weryfikacyjny na podany adres e-mail. Klasa ta obsługuje wprowadzanie kodu weryfikacyjnego przez użytkownika i aktualizuje stan weryfikacji w bazie danych.
   - `VerificationTask.java`: Klasa asynchroniczna odpowiedzialna za komunikację z bazą danych w celu weryfikacji kodu i aktualizacji statusu weryfikacji użytkownika.

3. **Połączenie z Bazą Danych i Przesyłanie E-maili**:
   - `DatabaseConnectionTask.java`: Klasa asynchroniczna odpowiedzialna za nawiązywanie połączenia z bazą danych PostgreSQL w celu zapisywania danych użytkowników i wykonywania innych operacji na danych.
   - `JavaMailAPI.java`: Klasa asynchroniczna odpowiedzialna za wysyłanie wiadomości e-mail z kodem weryfikacyjnym do użytkowników.

4. **Obliczanie Wskaźników Kondycji**:
   - `BmiCalculator.java`: Fragment odpowiedzialny za obliczanie wskaźnika masy ciała (BMI) na podstawie wprowadzonych danych użytkownika (waga i wzrost).
   - `CalorieCalculator.java`: Fragment odpowiedzialny za obliczanie dziennego zapotrzebowania kalorycznego na podstawie danych użytkownika (waga, wzrost, wiek, płeć i poziom aktywności fizycznej).

5. **Ćwiczenia Fizyczne**:
   - `ExercisesFragment.java`: Fragment prezentujący interfejs użytkownika do wyboru grupy mięśniowej, dla której użytkownik chce wyświetlić ćwiczenia.
   - `Exercises.java`: Aktywność wyświetlająca listę ćwiczeń dla wybranej grupy mięśniowej, wraz z linkami do filmów instruktażowych na YouTube.
   - `BackExercises.java`: Aktywność prezentująca obraz ludzkiego ciała z możliwością wyboru grupy mięśniowej.

6. **Główna Aktywność i Nawigacja**:
   - `MainActivity.java`: Główna aktywność aplikacji, zarządzająca nawigacją między różnymi fragmentami i modułami za pomocą DrawerLayout i NavigationView.

Projekt wykorzystuje również inne klasy pomocnicze, takie jak `Utils.java`, która przechowuje stałe wartości, np. dane do logowania do serwera poczty e-mail.

Aplikacja łączy się z bazą danych PostgreSQL hostowaną na zdalnym serwerze w celu przechowywania i zarządzania danymi użytkowników, takich jak dane logowania, weryfikacja adresu e-mail i inne informacje związane z kontem użytkownika.

Ogólnie rzecz biorąc, projekt jest kompleksową aplikacją fitness dla systemu Android, która oferuje użytkownikom narzędzia do monitorowania i poprawy kondycji fizycznej, takie jak obliczanie BMI, zapotrzebowania kalorycznego oraz dostęp do ćwiczeń fizycznych z instrukcjami wideo.
