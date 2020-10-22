Spring Security Demo Project that includes:

  * Two roles: Admin and standard users
  * Admin manages roles of any other admin and all users
  * Web page showing all users
  * Web page showing details of any user
  * Automated Admin creation in case of no admins




Business parameters:

  * Horse Ranch constraints on properties
    * Number of Acres
      * Minimum:  2; because less than that is not really enough for barn, horse arena, etc
      * Maximum:  100,000; because anything entered larger than that is very likely a mistake
    * Horse Capacity; number of horses that can be kept on property
      * Minimum:  2; because no fun riding alone
      * Maximum:  1,000; because anything larger is likely a mistake
    * People Capacity; number of subscriber people who can be accommodated at once...taking into account parking, etc.
      * Minimum:  2; because no fun riding alone
      * Maximum:  100; because anything larger is likely a mistake
    *  Subscription Price; should be rounded to whole dollars
      * Minimum $100; because anything less is not practical
      * Maximum $100,000; because even if 5 people stayed continuously for a year, that is an almost incredibly high price
    * Ranch Name Min/max
      * Minimum:  5 characters, no restriction on type
      * Maximum:  10 characters
    * Ranch Description; Should provide all the information a prospect should want before calling for more information
      * Minimum:  5 characters
      * Maximum:  250 characters
      

Other Business notes:

* A single subscriber represents the payer and up to 4 guests for any visit.  More than four would require special permission

    


Note:  constraints will be stored at top of service classes used for validations and possible in entity class with annotations such as @Min


Developer side features:
  * Roles table self populates
  * Security between Owners and Guests and between Admins and others
  * Validation for price allows user to enter $ and commas, but results stored as string
  * Spring Validator including custom methods used with messages.properties



Scratch for entry page

Horse Ranch Community is a brokerage service for equestrians connecting enthusiasts with owners of horse ranches available for subscription.

Sign up here and apply to automatically be assigned the browser role, and request access as one or both of these roles:
  Guest:  Looking to subscribe or have already subscribe to one or more ranches
  Owner:  Have one or more ranches to be offered on a subscription basis

  








  