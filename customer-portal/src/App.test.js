import { render, screen } from '@testing-library/react';
import App from './App';
import SignInPage from './molecules/SignInPage'
import { BrowserRouter } from 'react-router-dom';
import RegistrationPage from './molecules/RegistrationPage';
import RegistrationService from './services/RegistrationService';
import AccountPage from './molecules/AccountPage';
import TransactionService from './services/TransactionService';

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
  //account page
  test('renders the account page', () => {
    render(
      <BrowserRouter>
        <AccountPage />
      </BrowserRouter>
    );
  })

  test('search bar is displayed', () => {
    const {container, getByLabelText} = render(<AccountPage/>);
    const searchInput = getByLabelText('search_transactions');
    expect(searchInput).toBeDefined;
    expect(searchInput).toHaveAttribute("placeholder", "Search Transactions");
  })

  test('account panel is displayed', () => {
    const {container, getByLabelText} = render(<AccountPage/>);
    const accountTabs = getByLabelText('account_tabs');
    expect(accountTabs).toBeDefined;
    expect(accountTabs).toHaveTextContent("Transactions");
  })

  test('sort/filter form is displayed', () => {
    const {container, getByLabelText} = render(<AccountPage/>);
    const sortForm = getByLabelText('transaction_form');
    expect(sortForm).toBeDefined;
  })

  test('transaction params are created properly', () => {
    const params1 = TransactionService.getRequestParams();
    expect(params1.description).toBeNull;
    expect(params1.sortProp).toBeNull;
    expect(params1.page).toBeNull;
    expect(params1.size).toBeNull;

    const params2 = TransactionService.getRequestParams("desp", "sort", 0);
    expect(params2.description).toEqual("desp");
    expect(params2.sortProp).toEqual("sort");
    expect(params2.page).toEqual(0);
    expect(params2.size).toBeNull;

    const params3 = TransactionService.getRequestParams("desp", "sort", 0, 6);
    expect(params3.description).toEqual("desp");
    expect(params3.sortProp).toEqual("sort");
    expect(params3.page).toEqual(0);
    expect(params3.size).toEqual(6);
    
    const params4 = TransactionService.getRequestParams("", "");
    expect(params4.description).toBeNull;
    expect(params4.sortProp).toBeNull;
    expect(params4.page).toBeNull;
    expect(params4.size).toBeNull;
  })