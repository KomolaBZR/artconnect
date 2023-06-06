package com.artconnect.backend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class UserTests {

    @Nested
    @DisplayName("getAuthorities method")
    class GetAuthorities {

        @Test
        @DisplayName("should return user authorities based on the assigned role")
        void testGetAuthorities() {
            // Create a user with a role
            Role role = Role.ADMIN;  // Replace with the appropriate role constant from your implementation
            User user = User.builder()
                    .role(role)
                    .build();

            // Verify that the user's authorities match the role's authorities
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            assertEquals(role.getAuthorities(), authorities);
        }
    }

    @Nested
    @DisplayName("UserDetails methods")
    class UserDetailsMethods {

        @Test
        @DisplayName("isAccountNonExpired should always return true")
        void testIsAccountNonExpired() {
            User user = new User();
            assertTrue(user.isAccountNonExpired());
        }

        @Test
        @DisplayName("isAccountNonLocked should always return true")
        void testIsAccountNonLocked() {
            User user = new User();
            assertTrue(user.isAccountNonLocked());
        }

        @Test
        @DisplayName("isCredentialsNonExpired should always return true")
        void testIsCredentialsNonExpired() {
            User user = new User();
            assertTrue(user.isCredentialsNonExpired());
        }

        @Test
        @DisplayName("isEnabled should return the correct enabled status")
        void testIsEnabled() {
            User user = User.builder()
                    .isEnabled(true) // Set isEnabled explicitly
                    .build();
            assertTrue(user.isEnabled());
        }
    }

    @Nested
    @DisplayName("Getter methods")
    class GetterMethods {

        @Test
        @DisplayName("getId should return the correct ID")
        void testGetId() {
            User user = new User();
            String expectedId = "123";
            user.setId(expectedId);
            assertEquals(expectedId, user.getId());
        }

        @Test
        @DisplayName("getFirstname should return the correct firstname")
        void testGetFirstname() {
            User user = new User();
            String expectedFirstname = "John";
            user.setFirstname(expectedFirstname);
            assertEquals(expectedFirstname, user.getFirstname());
        }

        @Test
        @DisplayName("getLastname should return the correct lastname")
        void testGetLastname() {
            User user = new User();
            String expectedLastname = "Doe";
            user.setLastname(expectedLastname);
            assertEquals(expectedLastname, user.getLastname());
        }

        @Test
        @DisplayName("getEmail should return the correct email")
        void testGetEmail() {
            User user = new User();
            String expectedEmail = "john.doe@example.com";
            user.setEmail(expectedEmail);
            assertEquals(expectedEmail, user.getEmail());
        }

        @Test
        @DisplayName("getPassword should return the correct password")
        void testGetPassword() {
            User user = new User();
            String expectedPassword = "password123";
            user.setPassword(expectedPassword);
            assertEquals(expectedPassword, user.getPassword());
        }

        @Test
        @DisplayName("getCreatedAt should return the correct creation date")
        void testGetCreatedAt() {
            User user = new User();
            Date expectedCreatedAt = new Date();
            user.setCreatedAt(expectedCreatedAt);
            assertEquals(expectedCreatedAt, user.getCreatedAt());
        }

        @Test
        @DisplayName("isEnabled should return the correct enabled status")
        void testIsEnabled() {
            User user = new User();
            boolean expectedEnabled = true;
            user.setEnabled(expectedEnabled);
            assertEquals(expectedEnabled, user.isEnabled());
        }

        @Test
        @DisplayName("getRole should return the correct role")
        void testGetRole() {
            User user = new User();
            Role expectedRole = Role.ADMIN;
            user.setRole(expectedRole);
            assertEquals(expectedRole, user.getRole());
        }

        @Test
        @DisplayName("isAccountNonExpired should always return true")
        void testIsAccountNonExpired() {
            User user = new User();
            assertTrue(user.isAccountNonExpired());
        }

        @Test
        @DisplayName("isAccountNonLocked should always return true")
        void testIsAccountNonLocked() {
            User user = new User();
            assertTrue(user.isAccountNonLocked());
        }

        @Test
        @DisplayName("isCredentialsNonExpired should always return true")
        void testIsCredentialsNonExpired() {
            User user = new User();
            assertTrue(user.isCredentialsNonExpired());
        }
    }

    @Nested
    @DisplayName("Setter methods")
    class SetterMethods {

        @Test
        @DisplayName("setId should set the correct ID")
        void testSetId() {
            User user = new User();
            String expectedId = "123";
            user.setId(expectedId);
            assertEquals(expectedId, user.getId());
        }

        @Test
        @DisplayName("setFirstname should set the correct firstname")
        void testSetFirstname() {
            User user = new User();
            String expectedFirstname = "John";
            user.setFirstname(expectedFirstname);
            assertEquals(expectedFirstname, user.getFirstname());
        }

        @Test
        @DisplayName("setLastname should set the correct lastname")
        void testSetLastname() {
            User user = new User();
            String expectedLastname = "Doe";
            user.setLastname(expectedLastname);
            assertEquals(expectedLastname, user.getLastname());
        }

        @Test
        @DisplayName("setEmail should set the correct email")
        void testSetEmail() {
            User user = new User();
            String expectedEmail = "john.doe@example.com";
            user.setEmail(expectedEmail);
            assertEquals(expectedEmail, user.getEmail());
        }

        @Test
        @DisplayName("setPassword should set the correct password")
        void testSetPassword() {
            User user = new User();
            String expectedPassword = "password123";
            user.setPassword(expectedPassword);
            assertEquals(expectedPassword, user.getPassword());
        }

        @Test
        @DisplayName("setCreatedAt should set the correct creation date")
        void testSetCreatedAt() {
            User user = new User();
            Date expectedCreatedAt = new Date();
            user.setCreatedAt(expectedCreatedAt);
            assertEquals(expectedCreatedAt, user.getCreatedAt());
        }

        @Test
        @DisplayName("setEnabled should set the correct enabled status")
        void testSetEnabled() {
            User user = new User();
            boolean expectedEnabled = true;
            user.setEnabled(expectedEnabled);
            assertEquals(expectedEnabled, user.isEnabled());
        }

        @Test
        @DisplayName("setRole should set the correct role")
        void testSetRole() {
            User user = new User();
            Role expectedRole = Role.ADMIN;
            user.setRole(expectedRole);
            assertEquals(expectedRole, user.getRole());
        }
    }



    @Test
    void testGetPassword() {
        // Create a User object with a password
        String expectedPassword = "myPassword";
        User user = User.builder()
                .password(expectedPassword)
                .build();

        // Call the getPassword() method and assert the result
        String actualPassword = user.getPassword();
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testGetUsername() {
        // Create a User object with an email
        String expectedEmail = "user@example.com";
        User user = User.builder()
                .email(expectedEmail)
                .build();

        // Call the getUsername() method and assert the result
        String actualEmail = user.getUsername();
        assertEquals(expectedEmail, actualEmail);
    }

    @Nested
    @DisplayName("toString method")
    class ToStringMethod {

        @Test
        @DisplayName("toString should return a non-null string representation of the User object")
        void testToStringNotNull() {
            User user = User.builder()
                    .id("123")
                    .firstname("John")
                    .lastname("Doe")
                    .email("john.doe@example.com")
                    .password("password123")
                    .createdAt(new Date())
                    .isEnabled(true)
                    .role(Role.ADMIN)
                    .build();

            String toString = user.toString();
            assertNotNull(toString);
        }

        @Test
        @DisplayName("toString should include all field values")
        void testToStringFieldValues() {
            String id = "123";
            String firstname = "John";
            String lastname = "Doe";
            String email = "john.doe@example.com";
            String password = "password123";
            Date createdAt = new Date();
            boolean isEnabled = true;
            Role role = Role.ADMIN;

            User user = new User();
            user.setId(id);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setEmail(email);
            user.setPassword(password);
            user.setCreatedAt(createdAt);
            user.setEnabled(isEnabled);
            user.setRole(role);

            String toString = user.toString();
            assertTrue(toString.contains(id));
            assertTrue(toString.contains(firstname));
            assertTrue(toString.contains(lastname));
            assertTrue(toString.contains(email));
            assertTrue(toString.contains(password));
            assertTrue(toString.contains(createdAt.toString()));
            assertTrue(toString.contains(String.valueOf(isEnabled)));
            assertTrue(toString.contains(role.toString()));
        }
    }


    @Nested
    @DisplayName("Equals and HashCode")
    class EqualsAndHashCode {

        @Test
        @DisplayName("equals should return true for the same User instance")
        void testEqualsSameInstance() {
            User user = new User();
            assertTrue(user.equals(user));
        }

        @Test
        @DisplayName("equals should return true for equal User instances")
        void testEqualsEqualInstances() {
            User user1 = User.builder().id("123").build();
            User user2 = User.builder().id("123").build();
            assertTrue(user1.equals(user2));
            assertTrue(user2.equals(user1));
        }

        @Test
        @DisplayName("equals should return false for different User instances")
        void testEqualsDifferentInstances() {
            User user1 = User.builder().id("123").build();
            User user2 = User.builder().id("456").build();
            assertFalse(user1.equals(user2));
            assertFalse(user2.equals(user1));
        }

        @Test
        @DisplayName("equals should return false for null input")
        void testEqualsNull() {
            User user = new User();
            assertFalse(user.equals(null));
        }

        @Test
        @DisplayName("equals should return false for different class")
        void testEqualsDifferentClass() {
            User user = new User();
            Object otherObject = new Object();
            assertFalse(user.equals(otherObject));
        }


        @Test
        @DisplayName("hashCode should return the same value for equal User instances")
        void testHashCodeEqualInstances() {
            User user1 = User.builder().id("123").build();
            User user2 = User.builder().id("123").build();
            assertEquals(user1.hashCode(), user2.hashCode());
        }

        @Test
        @DisplayName("hashCode should return different values for different User instances")
        void testHashCodeDifferentInstances() {
            User user1 = User.builder().id("123").build();
            User user2 = User.builder().id("456").build();
            assertNotEquals(user1.hashCode(), user2.hashCode());
        }

        @Test
        @DisplayName("equals should return false when comparing with User instance with null ID")
        void testEqualsWithNullId() {
            User user1 = User.builder().id(null).build();
            User user2 = User.builder().id("123").build();
            assertFalse(user1.equals(user2));
            assertFalse(user2.equals(user1));
        }

        @Test
        @DisplayName("equals should return false when comparing with User instance with null properties")
        void testEqualsWithNullProperties() {
            User user1 = User.builder().id("123").build();
            User user2 = User.builder().build();
            assertFalse(user1.equals(user2));
            assertFalse(user2.equals(user1));
        }

        @Test
        @DisplayName("hashCode should return the same value for User instances with null ID")
        void testHashCodeWithNullId() {
            User user1 = User.builder().id(null).build();
            User user2 = User.builder().id(null).build();
            assertEquals(user1.hashCode(), user2.hashCode());
        }
    }

    @Nested
    @DisplayName("@RequiredArgsConstructor")
    class RequiredArgsConstructor {

        @Test
        @DisplayName("Constructor should assign values correctly")
        void testConstructor() {
            String id = "123";
            String firstname = "John";
            String lastname = "Doe";
            String email = "john.doe@example.com";
            String password = "password123";
            Date createdAt = new Date();
            boolean isEnabled = true;
            Role role = Role.ADMIN;

            User user = new User(id, firstname, lastname, email, password, createdAt, isEnabled, role);

            assertEquals(id, user.getId());
            assertEquals(firstname, user.getFirstname());
            assertEquals(lastname, user.getLastname());
            assertEquals(email, user.getEmail());
            assertEquals(password, user.getPassword());
            assertEquals(createdAt, user.getCreatedAt());
            assertEquals(isEnabled, user.isEnabled());
            assertEquals(role, user.getRole());
        }

        @Test
        @DisplayName("Constructor should assign default values correctly")
        void testConstructorWithDefaultValues() {
            User user = new User();

            // Verify that default values are assigned correctly
            assertEquals(null, user.getId());
            assertEquals(null, user.getFirstname());
            assertEquals(null, user.getLastname());
            assertEquals(null, user.getEmail());
            assertEquals(null, user.getPassword());
            assertEquals(null, user.getCreatedAt());
            assertEquals(false, user.isEnabled());
            assertEquals(null, user.getRole());
        }

        @Test
        @DisplayName("Constructor should assign null values correctly")
        void testConstructorWithNullValues() {
            String expectedId = null;
            String expectedFirstname = null;
            String expectedLastname = null;
            String expectedEmail = null;
            String expectedPassword = null;
            Date expectedCreatedAt = null;
            boolean expectedIsEnabled = false;
            Role expectedRole = null;

            User user = new User(
                    expectedId, expectedFirstname, expectedLastname, expectedEmail,
                    expectedPassword, expectedCreatedAt, expectedIsEnabled, expectedRole
            );

            // Verify that null values are assigned correctly
            assertEquals(expectedId, user.getId());
            assertEquals(expectedFirstname, user.getFirstname());
            assertEquals(expectedLastname, user.getLastname());
            assertEquals(expectedEmail, user.getEmail());
            assertEquals(expectedPassword, user.getPassword());
            assertEquals(expectedCreatedAt, user.getCreatedAt());
            assertEquals(expectedIsEnabled, user.isEnabled());
            assertEquals(expectedRole, user.getRole());
        }
    }
}