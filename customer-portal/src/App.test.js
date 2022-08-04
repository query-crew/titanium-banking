import { render, screen } from '@testing-library/react';
import App from './App';
import SignInPage from './molecules/SignInPage'
import { BrowserRouter } from 'react-router-dom';
import RegistrationPage from './molecules/RegistrationPage';
import RegistrationService from './services/RegistrationService';

test('renders the landing page', () => {
  render(<App />);
});

test('renders the signin page', () => {
  render(
      <BrowserRouter>
        <SignInPage />
      </BrowserRouter>
    )
  });

  test('renders the registration page', () => {
    render(
        <BrowserRouter>
          <RegistrationPage />
        </BrowserRouter>
      )
  });

  test('remember me checkbox is checked on sign in card', () => {
    localStorage.setItem("checked", "true");
    render(
      <App/>
    )
    expect(screen.getByTestId("sign-in-card-checkbox")).toBeChecked();
    localStorage.removeItem("checked");
  });

  test('remember me checkbox is checked on sign page', () => {
    localStorage.setItem("checked", "true");
    render(
        <BrowserRouter>
          <SignInPage />
        </BrowserRouter>
    )
    expect(screen.getByTestId("expanded-sign-in-card-checkbox")).toBeChecked();
    expect(screen.getByTestId("expanded-sign-in-checkbox")).toBeChecked();
    localStorage.removeItem("checked");
  });


  test('remember me checkbox is not checked on sign in card', () => {
    localStorage.setItem("checked", "false");
    render(
      <App/>
    )
    expect(screen.getByTestId("sign-in-card-checkbox")).not.toBeChecked();
    localStorage.removeItem("checked");
  });

  test('remember me checkbox is not checked on sign page', () => {
    localStorage.setItem("checked", "false");
    render(
        <BrowserRouter>
          <SignInPage />
        </BrowserRouter>
    )
    expect(screen.getByTestId("expanded-sign-in-card-checkbox")).not.toBeChecked();
    expect(screen.getByTestId("expanded-sign-in-checkbox")).not.toBeChecked();
    localStorage.removeItem("checked");
  });

  test('username is present and remember me is checked', () => {
    localStorage.setItem("checked", "true");
    localStorage.setItem("username", "test");
    render(
      <App/>
    )
    expect(screen.getByTestId("sign-in-card-checkbox")).toBeChecked();
    expect(screen.getByLabelText("Username")).toHaveValue("test");
    localStorage.removeItem("checked");
    localStorage.removeItem("username");
  });

  test('username is not present and remember me is not checked', () => {
    render(
      <App/>
    )
    expect(screen.getByTestId("sign-in-card-checkbox")).not.toBeChecked();
    expect(screen.getByLabelText("Username")).toHaveValue("");
  });

  test('checks to see if username is a valid', () => {
    expect(RegistrationService.isUsername("chloe")).toEqual(true);
  });

  test('checks to see if username is a valid and returns false', () => {
    expect(RegistrationService.isUsername("chloe!!!")).toEqual(false);
  });

  test('checks to see if name is a valid', () => {
    expect(RegistrationService.isName("chloe")).toEqual(true);
  });

  test('checks to see if name is a valid and returns false', () => {
    expect(RegistrationService.isName("chloe!!!")).toEqual(false);
  });

  test('checks to see if social is a valid', () => {
    expect(RegistrationService.isSocial("234-56-7890")).toEqual(true);
  });

  test('checks to see if social is a valid and returns false', () => {
    expect(RegistrationService.isSocial("chloe!!!")).toEqual(false);
  });

  test('checks to see if address line 1 is a valid', () => {
    expect(RegistrationService.isAddressOne("1992 Pine. Ave.")).toEqual(true);
  });

  test('checks to see if address line 1 is a valid and returns false', () => {
    expect(RegistrationService.isAddressOne("<script/>")).toEqual(false);
  });
  
  test('checks to see if address line 2 is a valid', () => {
    expect(RegistrationService.isAddressTwo("")).toEqual(true);
  });

  test('checks to see if address line 2 is a valid and returns false', () => {
    expect(RegistrationService.isAddressTwo("<script/>")).toEqual(false);
  });

  test('checks to see if city is a valid', () => {
    expect(RegistrationService.isCity("Boise")).toEqual(true);
  });

  test('checks to see if city is a valid and returns false', () => {
    expect(RegistrationService.isCity("24")).toEqual(false);
  });

  test('checks to see if state is a valid', () => {
    expect(RegistrationService.isState("Idaho")).toEqual(true);
  });

  test('checks to see if state is a valid and returns false', () => {
    expect(RegistrationService.isState("State")).toEqual(false);
  });
  
  test('checks to see if form is a valid', () => {
    expect(RegistrationService.formValid("chloejohnsoncodes@gmail.com", "chloe", "MyPassword1", "Chloe", 
    "Johnson", "(208)456-9999", "1998-12-15", "999-99-9999", "1992 Pine Ave", "", "Idaho", "Boise", "83713")).toEqual(true);
  });

  test('checks to see if form is a valid and returns false', () => {
    expect(RegistrationService.formValid("", "", "", "", "", "", "", "", "", "", "State", "", "")).toEqual(false);
  });