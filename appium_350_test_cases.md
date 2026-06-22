# SmartNotes AI — 350 Appium (Android) Test Cases

> **Project:** [note-repo](https://github.com/Ujwal-code-otha/note-repo)
> **Platform:** Android (Kotlin / Jetpack Compose)
> **Automation Tool:** Appium
> **Total Test Cases:** 350

---

## Legend

| Column | Description |
|--------|-------------|
| **TC#** | Unique test-case ID (APP-001 → APP-350) |
| **Category** | Feature area under test |
| **Test Case Title** | Short, descriptive name |
| **Preconditions** | State required before execution |
| **Steps** | Numbered execution steps |
| **Expected Result** | Pass criteria |

---

## 1 · App Launch & Splash Screen (APP-001 → APP-015)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-001 | App launches successfully | App installed | 1. Tap app icon | App opens without crash |
| APP-002 | Splash screen displays on launch | App installed | 1. Launch app | Splash screen with SmartNotes AI branding visible |
| APP-003 | Splash screen transitions to login | App launched | 1. Wait on splash | Auto-navigates to login screen within timeout |
| APP-004 | Splash screen gradient colors correct | App launched | 1. Observe splash | TechBlue and NeonPurple gradient visible |
| APP-005 | App does not crash on rapid re-launch | App installed | 1. Launch 2. Kill 3. Re-launch quickly | App opens normally |
| APP-006 | App launches in portrait mode | App installed | 1. Launch app in portrait | UI renders correctly |
| APP-007 | App launches in landscape mode | App installed | 1. Launch app in landscape | UI renders correctly or forces portrait |
| APP-008 | App icon visible in launcher | App installed | 1. Check app drawer | SmartNotes AI icon present |
| APP-009 | App label shows "SmartNotes AI" | App installed | 1. Check app name in launcher | "SmartNotes AI" displayed |
| APP-010 | Internet permission granted | App installed | 1. Check app permissions | INTERNET permission present |
| APP-011 | App handles no internet on launch | Airplane mode | 1. Launch app | App opens, shows offline state |
| APP-012 | App version displayed in settings | App launched | 1. Navigate to settings | Version number shown |
| APP-013 | App opens from recent apps | App in recents | 1. Open from recent apps tray | App resumes at last screen |
| APP-014 | App survives low memory situation | App in background | 1. Open many other apps 2. Return | App restores state or re-launches |
| APP-015 | First-launch onboarding (if present) | Fresh install | 1. Launch app first time | Onboarding or direct login shown |

---

## 2 · Login Screen (APP-016 → APP-055)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-016 | Login screen renders after splash | App launched | 1. Wait for splash to finish | Login screen with email, password fields visible |
| APP-017 | "SmartNotes AI" title displayed | Login screen | 1. Check heading | "SmartNotes AI" text with gradient brush |
| APP-018 | "Advanced Neural Login" subtitle shown | Login screen (email mode) | 1. Check subtitle | "Advanced Neural Login" text |
| APP-019 | Email field pre-filled with test email | Login screen | 1. Check email field | Pre-filled with `shreyassatishkumar@gmail.com` |
| APP-020 | Password field pre-filled with test password | Login screen | 1. Check password field | Pre-filled with `123456` |
| APP-021 | Auto-login fires after 1.5 seconds | Login screen | 1. Wait 1.5 seconds | Automatically navigates to home screen |
| APP-022 | AUTHORIZE button visible | Login screen | 1. Check buttons | "AUTHORIZE" button present |
| APP-023 | AUTHORIZE button has testTag | Login screen | 1. Find by testTag | `authorize_button` testTag found |
| APP-024 | Tap AUTHORIZE with valid credentials | Login screen | 1. Tap "AUTHORIZE" | Navigates to home screen |
| APP-025 | Tap AUTHORIZE with test credentials bypass | Login screen | 1. Enter test@example.com + password123 2. Tap AUTHORIZE | Direct navigation (no Firebase call) |
| APP-026 | Login with empty email | Login screen | 1. Clear email 2. Tap AUTHORIZE | Validation — does not proceed |
| APP-027 | Login with invalid email format | Login screen | 1. Enter "notanemail" 2. Tap AUTHORIZE | `isEmailInvalid` triggers, no login |
| APP-028 | Login with incorrect password (Firebase) | Login screen | 1. Enter valid email + wrong password 2. Tap AUTHORIZE | Error message displayed |
| APP-029 | Error text shown in red | Error triggered | 1. Trigger error | Error text with `MaterialTheme.colorScheme.error` color |
| APP-030 | Loading indicator during login | Login in progress | 1. Tap AUTHORIZE (non-test creds) | `CircularProgressIndicator` visible |
| APP-031 | Biometric button visible | Login screen | 1. Check icon button | Fingerprint icon button present |
| APP-032 | Tap biometric button shows prompt | Login screen | 1. Tap fingerprint icon | BiometricPrompt dialog appears |
| APP-033 | Biometric success navigates to home | Biometric enrolled, previously logged in | 1. Authenticate fingerprint | Navigates to home screen |
| APP-034 | Biometric failure shows toast | Login screen | 1. Fail fingerprint auth | Toast: "Authentication failed" |
| APP-035 | Biometric error shows toast | No biometric enrolled | 1. Tap fingerprint | Toast: "Authentication error: ..." |
| APP-036 | Biometric prompt title is "Biometric Login" | Biometric prompt | 1. Check prompt title | "Biometric Login" text |
| APP-037 | Biometric prompt subtitle correct | Biometric prompt | 1. Check subtitle | "Use your fingerprint or face to unlock" |
| APP-038 | "SMS Entry" button switches to phone login | Login screen | 1. Tap "SMS Entry" | Phone number field displayed |
| APP-039 | "Secure Phone Entry" subtitle in phone mode | Phone login mode | 1. Check subtitle | "Secure Phone Entry" text |
| APP-040 | Phone field shows "+91" prefix | Phone login mode | 1. Check phone field | Pre-filled with "+91" |
| APP-041 | "SEND OTP" button text in phone mode | Phone login mode | 1. Check button | "SEND OTP" text |
| APP-042 | OTP sent successfully | Phone mode, valid number | 1. Enter phone 2. Tap SEND OTP | OTP field appears |
| APP-043 | OTP field appears after code sent | OTP sent | 1. Check field | "6-Digit OTP" label shown |
| APP-044 | OTP limited to 6 characters | OTP field | 1. Enter 7 chars | Only 6 accepted |
| APP-045 | OTP verification success navigates home | Valid OTP | 1. Enter correct OTP 2. Tap AUTHORIZE | Navigates to home |
| APP-046 | OTP verification failure shows error | Invalid OTP | 1. Enter wrong OTP 2. Tap AUTHORIZE | Error message shown |
| APP-047 | "Neural Password" toggles back to email mode | Phone login mode | 1. Tap "Neural Password" | Email/password fields shown |
| APP-048 | "Register Identity" navigates to signup | Login screen | 1. Tap "Register Identity" | Signup screen opens |
| APP-049 | Keyboard ImeAction.Next on email field | Login screen | 1. Tap email field 2. Check keyboard action | Keyboard shows "Next" |
| APP-050 | Keyboard ImeAction.Done on password field | Login screen | 1. Tap password field 2. Check keyboard action | Keyboard shows "Done" |
| APP-051 | Keyboard hides on Done action | Password focused | 1. Tap keyboard "Done" | Keyboard dismissed |
| APP-052 | Password uses PasswordVisualTransformation | Login screen | 1. Check password display | Characters masked as dots |
| APP-053 | Background TechBlue glow visible | Login screen | 1. Check top-left | Blue circular glow element |
| APP-054 | Login screen scrollable on small device | Small screen | 1. Scroll content | All elements accessible |
| APP-055 | navigateToHome prevents double navigation | Rapid tap | 1. Tap AUTHORIZE rapidly | Only navigates once (hasNavigatedToHome check) |

---

## 3 · Signup Screen (APP-056 → APP-075)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-056 | Signup screen renders | Tapped "Register Identity" | 1. Check screen | Name, email, password fields visible |
| APP-057 | Signup with valid credentials | Signup screen | 1. Enter name, email, password 2. Tap Sign Up | Account created, navigates to home |
| APP-058 | Signup with empty name | Signup screen | 1. Leave name empty 2. Tap Sign Up | Validation error |
| APP-059 | Signup with empty email | Signup screen | 1. Leave email empty 2. Tap Sign Up | Validation error |
| APP-060 | Signup with empty password | Signup screen | 1. Leave password empty 2. Tap Sign Up | Validation error |
| APP-061 | Signup with existing email | Signup screen | 1. Enter existing email 2. Tap Sign Up | Error: "email already in use" |
| APP-062 | Signup with weak password (<6 chars) | Signup screen | 1. Enter "12" as password 2. Tap Sign Up | Firebase weak password error |
| APP-063 | Signup with invalid email format | Signup screen | 1. Enter "abc@" 2. Tap Sign Up | Validation error |
| APP-064 | Signup with password mismatch (if confirm present) | Signup screen | 1. Enter different passwords 2. Tap Sign Up | Error shown |
| APP-065 | Loading indicator during signup | Signup in progress | 1. Tap Sign Up | Spinner visible |
| APP-066 | Back navigation to login from signup | Signup screen | 1. Tap back button | Returns to login screen |
| APP-067 | Signup with special characters in name | Signup screen | 1. Enter "O'Brien" 2. Sign Up | Succeeds |
| APP-068 | Signup with Unicode name | Signup screen | 1. Enter "名前" 2. Sign Up | Succeeds or graceful error |
| APP-069 | Signup with very long name (200 chars) | Signup screen | 1. Enter 200-char name | Handled gracefully |
| APP-070 | Signup with email containing + sign | Signup screen | 1. Enter `user+1@gmail.com` | Succeeds |
| APP-071 | Keyboard type for email field is Email | Signup screen | 1. Focus email field | Email keyboard shown |
| APP-072 | Keyboard type for password is Password | Signup screen | 1. Focus password field | Password keyboard |
| APP-073 | Signup screen landscape orientation | Landscape | 1. Rotate device | Form usable and scrollable |
| APP-074 | Error message clears on new attempt | Error shown | 1. Fix fields 2. Re-submit | Error clears |
| APP-075 | Signup button text correct | Signup screen | 1. Check button | "Sign Up" or "REGISTER" text |

---

## 4 · Forgot Password Screen (APP-076 → APP-085)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-076 | Forgot password screen renders | Navigated from login | 1. Check screen | Email field and submit button visible |
| APP-077 | Submit valid email for reset | Registered email | 1. Enter email 2. Tap submit | Success message: reset email sent |
| APP-078 | Submit unregistered email | None | 1. Enter unknown email 2. Submit | Error or generic message |
| APP-079 | Submit empty email | Forgot password | 1. Tap submit with empty field | Validation error |
| APP-080 | Submit invalid email format | Forgot password | 1. Enter "abc" 2. Submit | Validation error |
| APP-081 | Back navigation to login | Forgot password screen | 1. Tap back | Returns to login |
| APP-082 | Loading indicator during submission | Submitting | 1. Submit email | Loading shown |
| APP-083 | Error text styling | Error triggered | 1. Trigger error | Error in red color |
| APP-084 | Success text styling | Success triggered | 1. Submit valid email | Success message shown |
| APP-085 | Keyboard dismissed after submit | Form submitted | 1. Submit | Keyboard hides |

---

## 5 · Home Screen (APP-086 → APP-115)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-086 | Home screen loads after login | Logged in | 1. Wait for navigation | Home screen with note list visible |
| APP-087 | Bottom navigation bar present | Home screen | 1. Check bottom | Bottom nav with icons visible |
| APP-088 | Home tab selected by default | Home screen | 1. Check bottom nav | Home tab highlighted |
| APP-089 | Note list displayed | User has notes | 1. Check main area | List of notes shown |
| APP-090 | Empty state for new user | No notes | 1. Login as new user | "No notes yet" or similar message |
| APP-091 | FAB (Floating Action Button) visible | Home screen | 1. Check bottom-right | Add note FAB present |
| APP-092 | Tap FAB opens Add Note screen | Home screen | 1. Tap FAB | Add Note screen opens |
| APP-093 | Note card shows title | Notes exist | 1. Check note card | Title text visible |
| APP-094 | Note card shows preview/snippet | Notes exist | 1. Check note card | Content preview shown |
| APP-095 | Note card shows date | Notes exist | 1. Check note card | Date/timestamp visible |
| APP-096 | Tap note opens NoteDetail screen | Notes exist | 1. Tap on a note card | NoteDetail screen opens |
| APP-097 | Pull to refresh note list | Home screen | 1. Pull down | Notes refreshed |
| APP-098 | Search icon visible | Home screen | 1. Check top bar | Search icon present |
| APP-099 | Tap search opens search | Home screen | 1. Tap search icon | Search input appears |
| APP-100 | Search filters notes | Multiple notes | 1. Type keyword | Filtered notes shown |
| APP-101 | Search with no results | Notes exist | 1. Search "xyznonexistent" | Empty results message |
| APP-102 | Bottom nav — Notes tab | Home screen | 1. Tap Notes tab | Notes screen shown |
| APP-103 | Bottom nav — Quiz tab | Home screen | 1. Tap Quiz tab | Quiz screen shown |
| APP-104 | Bottom nav — Chat tab | Home screen | 1. Tap Chat tab | ChatBot screen shown |
| APP-105 | Bottom nav — Profile tab | Home screen | 1. Tap Profile tab | Profile screen shown |
| APP-106 | Swipe left on note for delete | Note in list | 1. Swipe left | Delete option revealed |
| APP-107 | Long press note for options | Note in list | 1. Long press | Context menu with delete/edit options |
| APP-108 | Note list scrolls smoothly | Many notes (20+) | 1. Scroll up and down | Smooth scrolling, no jank |
| APP-109 | Screen rotation preserves state | Home screen | 1. Rotate device | Note list preserved |
| APP-110 | Note count badge (if present) | Notes exist | 1. Check UI | Note count shown |
| APP-111 | Home screen loads within 2 seconds | Logged in | 1. Measure load time | Under 2 seconds |
| APP-112 | App bar title shows app name | Home screen | 1. Check app bar | "SmartNotes AI" or similar |
| APP-113 | Settings icon in app bar | Home screen | 1. Check top bar | Settings gear icon present |
| APP-114 | Tap settings icon opens Settings | Home screen | 1. Tap settings | Settings screen opens |
| APP-115 | Hardware back button on home | Home screen | 1. Press back button | App minimizes or exit confirmation |

---

## 6 · Add/Edit Note Screen (APP-116 → APP-145)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-116 | Add Note screen renders | Tapped FAB | 1. Check screen | Title and content fields visible |
| APP-117 | Enter note title | Add Note screen | 1. Type title text | Title entered |
| APP-118 | Enter note content | Add Note screen | 1. Type content text | Content entered |
| APP-119 | Save note with title and content | Filled form | 1. Tap Save | Note saved, returns to home |
| APP-120 | Save note with empty title | Add Note screen | 1. Leave title empty 2. Save | Validation or default title assigned |
| APP-121 | Save note with empty content | Add Note screen | 1. Leave content empty 2. Save | Validation error or saved |
| APP-122 | Save note toast confirmation | Note saved | 1. Save note | Toast "Note saved" displayed |
| APP-123 | Back button without saving shows discard dialog | Content entered | 1. Type content 2. Tap back | "Discard changes?" dialog |
| APP-124 | Note title max length handling | Add Note screen | 1. Enter 500-char title | Handled gracefully |
| APP-125 | Note content max length handling | Add Note screen | 1. Enter 10000 chars | Saved successfully |
| APP-126 | Note with special characters `@#$%^&*` | Add Note screen | 1. Enter specials 2. Save | Preserved correctly |
| APP-127 | Note with emoji content 📚🎓 | Add Note screen | 1. Enter emojis 2. Save | Emojis preserved |
| APP-128 | Note with multiline content | Add Note screen | 1. Enter paragraphs 2. Save | Line breaks preserved |
| APP-129 | Edit existing note title | Note Detail screen | 1. Tap edit 2. Change title 3. Save | Title updated |
| APP-130 | Edit existing note content | Note Detail screen | 1. Tap edit 2. Change content 3. Save | Content updated |
| APP-131 | Delete note from detail screen | Note Detail screen | 1. Tap delete 2. Confirm | Note deleted, returns to home |
| APP-132 | Cancel delete from detail screen | Delete dialog | 1. Tap cancel | Note not deleted |
| APP-133 | Note detail shows full content | Note exists | 1. Tap note | Full content displayed |
| APP-134 | Note detail shows title | Note exists | 1. Check heading | Title displayed |
| APP-135 | Note detail scrollable for long content | Long note | 1. Scroll content | All content accessible |
| APP-136 | Keyboard appears when tapping field | Add Note screen | 1. Tap title field | Keyboard opens |
| APP-137 | Keyboard dismissed on tap outside | Keyboard open | 1. Tap outside fields | Keyboard dismissed |
| APP-138 | Note creation in landscape mode | Landscape | 1. Rotate 2. Create note | UI usable |
| APP-139 | Voice input button (if present) | Add Note screen | 1. Tap microphone | Voice recognition starts |
| APP-140 | Voice transcription appears in content | Voice input active | 1. Speak | Transcribed text in content field |
| APP-141 | Offline note creation | Airplane mode | 1. Create note | Saved locally |
| APP-142 | Offline note syncs when online | Note created offline | 1. Turn on internet | Note synced to cloud |
| APP-143 | Auto-save during editing | Editing note | 1. Type content 2. Wait | Content auto-saved |
| APP-144 | Note category/tag assignment | Add Note screen | 1. Select tag/category 2. Save | Tag saved with note |
| APP-145 | Note with RTL text (Arabic/Hebrew) | Add Note screen | 1. Enter Arabic text 2. Save | RTL text preserved |

---

## 7 · AI Summarization (APP-146 → APP-165)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-146 | Summarize button visible on note detail | Note with content | 1. Check note detail screen | "Summarize" or AI button visible |
| APP-147 | Tap Summarize generates summary | Note with 200+ chars | 1. Tap Summarize | Summary text displayed below note |
| APP-148 | Summary loading indicator | Summarizing | 1. Tap Summarize | Loading spinner visible |
| APP-149 | Summary error without internet | Airplane mode | 1. Tap Summarize | Error toast/message shown |
| APP-150 | Summary error when API fails | API error | 1. Tap Summarize | Error: "Neural synthesis failed" |
| APP-151 | Summary formatted as bullet points | Summary generated | 1. Check format | Bullet points displayed |
| APP-152 | Re-summarize same note | Previously summarized | 1. Tap Summarize again | New summary generated |
| APP-153 | Summary preserves original note | Note open | 1. Summarize 2. Check note | Original content unchanged |
| APP-154 | Summarize short note (<10 words) | Short note | 1. Tap Summarize | Brief summary or message |
| APP-155 | Summarize long note (10000 chars) | Long note | 1. Tap Summarize | Summary generated |
| APP-156 | Copy summary to clipboard | Summary shown | 1. Long press summary 2. Tap Copy | Text copied, toast confirmation |
| APP-157 | Share summary via Android share | Summary shown | 1. Tap share icon | Android share sheet opens |
| APP-158 | Summary scrollable if long | Long summary | 1. Scroll summary area | Full summary accessible |
| APP-159 | Summarize PDF-imported note | PDF note | 1. Summarize | Summary of PDF content |
| APP-160 | Summarize note with code | Code note | 1. Summarize | Code concepts summarized |
| APP-161 | Summarize non-English note | Spanish note | 1. Summarize | Summary generated |
| APP-162 | Summary display on small screen | Small device | 1. Check summary | Readable, no text overflow |
| APP-163 | Summarize with background app | App in background | 1. Start summarize 2. Switch app 3. Return | Summary completed |
| APP-164 | Summarize empty note | Empty note | 1. Tap Summarize | Validation error or message |
| APP-165 | Summary animation on display | Summary appears | 1. Check animation | Smooth fade-in or slide-in |

---

## 8 · AI Quiz (APP-166 → APP-195)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-166 | Quiz screen accessible from bottom nav | Home screen | 1. Tap Quiz tab | Quiz screen opens |
| APP-167 | Generate quiz from note content | Note with content | 1. Select note 2. Tap Generate Quiz | Quiz with MCQs displayed |
| APP-168 | Quiz shows question text | Quiz active | 1. Check question area | Question text visible |
| APP-169 | Quiz shows 4 MCQ options | Quiz active | 1. Check options | 4 options displayed |
| APP-170 | Tap correct answer highlights green | Quiz active | 1. Tap correct option | Green highlight applied |
| APP-171 | Tap wrong answer highlights red | Quiz active | 1. Tap wrong option | Red highlight applied |
| APP-172 | Score counter increments on correct | Correct answer | 1. Tap correct | Score goes up by 1 |
| APP-173 | Next question button advances | Answer selected | 1. Tap Next | Next question shown |
| APP-174 | Quiz progress indicator | Quiz in progress | 1. Check progress | "2/5" or progress bar |
| APP-175 | Quiz loading during generation | Generating quiz | 1. Tap Generate | Loading animation |
| APP-176 | Quiz error without internet | Airplane mode | 1. Generate quiz | Error toast |
| APP-177 | Quiz results screen | Quiz completed | 1. Answer all | Score summary displayed |
| APP-178 | Quiz retry button | Results screen | 1. Tap Retry | Same quiz restarts |
| APP-179 | Quiz new quiz button | Results screen | 1. Tap New Quiz | Different quiz generated |
| APP-180 | Quiz from empty note | Empty note | 1. Generate quiz | Error message |
| APP-181 | Quiz answer persistence during scroll | Many questions | 1. Answer Q1 2. Scroll 3. Return | Q1 answer preserved |
| APP-182 | Quiz back navigation | Quiz active | 1. Tap back | Confirmation or returns to notes |
| APP-183 | Quiz with long question text | Long question | 1. Check display | Text wraps, fully readable |
| APP-184 | Quiz with long option text | Long options | 1. Check options | Text wraps properly |
| APP-185 | Quiz landscape orientation | Landscape | 1. Rotate device | Quiz UI adapts |
| APP-186 | Quiz score sharing | Results screen | 1. Tap Share | Android share sheet with score |
| APP-187 | Quiz timer (if present) | Quiz active | 1. Check timer | Countdown timer visible |
| APP-188 | Quiz timer expiry auto-advances | Timer at 0 | 1. Wait for expiry | Auto-advances to next |
| APP-189 | Quiz XP earned displayed | Quiz completed | 1. Check results | XP earned shown |
| APP-190 | Quiz from very long note | 10000 char note | 1. Generate quiz | Quiz generated |
| APP-191 | Quiz correct answer revealed after submit | Quiz done | 1. Check questions | Correct answers shown |
| APP-192 | Quiz accessibility — screen reader | Quiz active | 1. Enable TalkBack | Options announced |
| APP-193 | Quiz performance — no lag | Quiz active | 1. Tap options | Instant response (<500ms) |
| APP-194 | Quiz history saved | Quiz completed | 1. Navigate to analytics | Quiz history recorded |
| APP-195 | Quiz notification badge (if applicable) | New quiz available | 1. Check badges | Notification indicator |

---

## 9 · AI Flashcards (APP-196 → APP-220)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-196 | Flashcard screen accessible | Note detail screen | 1. Tap Flashcards button | Flashcard view opens |
| APP-197 | Flashcards generated from note | Note with content | 1. Tap Generate | 5 flashcards created |
| APP-198 | Flashcard shows front (question) | Flashcard view | 1. Check card | Front text displayed |
| APP-199 | Tap to flip flashcard | Front showing | 1. Tap card | Card flips to show back (answer) |
| APP-200 | Flip animation smooth | Tapping card | 1. Tap to flip | Smooth flip animation |
| APP-201 | Back shows answer text | Card flipped | 1. Check back | Answer content visible |
| APP-202 | Tap flipped card returns to front | Back showing | 1. Tap again | Returns to front |
| APP-203 | Swipe right for next card | Flashcard view | 1. Swipe right | Next card shown |
| APP-204 | Swipe left for previous card | Card 2+ | 1. Swipe left | Previous card shown |
| APP-205 | Card counter "1/5" displayed | First card | 1. Check counter | "1/5" shown |
| APP-206 | Progress dots/bar shown | Flashcard view | 1. Check indicators | Progress visible |
| APP-207 | Cannot swipe before first card | First card | 1. Swipe left | Nothing happens / bounce |
| APP-208 | Last card completion action | Last card | 1. Swipe right | Completion screen or loop |
| APP-209 | Flashcard loading indicator | Generating | 1. Tap Generate | Loading animation |
| APP-210 | Flashcard error without internet | Airplane mode | 1. Generate flashcards | Error toast |
| APP-211 | Flashcard from empty note | Empty note | 1. Generate | Error message |
| APP-212 | Flashcard text readability | Card displayed | 1. Check font size and contrast | Text clearly readable |
| APP-213 | Flashcard landscape orientation | Landscape | 1. Rotate device | Card displays properly |
| APP-214 | Flashcard with long front text | Long question | 1. Check card | Text wraps or scrolls |
| APP-215 | Flashcard with long back text | Long answer | 1. Flip card | Text scrollable |
| APP-216 | Flashcard share result | Card flipped | 1. Tap share | Share content via Android share |
| APP-217 | Flashcard from PDF note | PDF note | 1. Generate flashcards | Cards from PDF content |
| APP-218 | Flashcard session reset | End of session | 1. Tap restart | Returns to card 1 |
| APP-219 | Flashcard back navigation | Flashcard view | 1. Tap back | Returns to note detail |
| APP-220 | Flashcard with code content | Code note | 1. Generate flashcards | Code-related Q&A cards |

---

## 10 · AI Chatbot (APP-221 → APP-250)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-221 | ChatBot screen accessible from nav | Home screen | 1. Tap Chat tab | ChatBot screen opens |
| APP-222 | Chat input field visible | Chat screen | 1. Check bottom area | Text input with send button |
| APP-223 | Send text message | Chat screen | 1. Type message 2. Tap send | Message displayed in chat |
| APP-224 | AI response received | Message sent | 1. Wait for response | AI response bubble shown |
| APP-225 | Chat loading indicator | Message sent | 1. Check UI | Typing indicator visible |
| APP-226 | Empty message not sent | Chat screen | 1. Tap send with empty input | Nothing sent |
| APP-227 | Chat keyboard opens on tap | Chat screen | 1. Tap input field | Keyboard appears |
| APP-228 | Keyboard dismissed after send | Message sent | 1. Check keyboard | Keyboard auto-dismisses or stays |
| APP-229 | Chat scrolls to latest message | Multiple messages | 1. Send message | Auto-scrolls to bottom |
| APP-230 | Chat scroll to older messages | Many messages | 1. Scroll up | Older messages visible |
| APP-231 | User message right-aligned | Chat with messages | 1. Check user messages | Right-side alignment |
| APP-232 | AI message left-aligned | Chat with messages | 1. Check AI messages | Left-side alignment |
| APP-233 | Chat with emoji 🤖📚 | Chat screen | 1. Send emoji message | Emojis displayed correctly |
| APP-234 | Chat with long message (5000 chars) | Chat screen | 1. Paste long text 2. Send | Message sent, scrollable |
| APP-235 | Chat error without internet | Airplane mode | 1. Send message | Error toast: connection error |
| APP-236 | Voice input button (if present) | Chat screen | 1. Tap mic icon | Voice recognition starts |
| APP-237 | Voice message transcribed | Voice active | 1. Speak | Text transcribed to input |
| APP-238 | Chat history persisted | Leave and return | 1. Navigate away 2. Return | Previous messages visible |
| APP-239 | Chat with XSS payload | Chat screen | 1. Send `<script>alert(1)</script>` | Plaintext shown, not executed |
| APP-240 | Chat landscape mode | Landscape | 1. Rotate device | Chat UI adapts |
| APP-241 | Chat performance — smooth scrolling | Many messages | 1. Scroll rapidly | No jank or stuttering |
| APP-242 | Chat input max lines | Chat screen | 1. Type many lines | Input area grows then scrolls |
| APP-243 | Multiple rapid messages | Chat screen | 1. Send 5 messages fast | All processed in order |
| APP-244 | Chat message timestamp (if shown) | Messages exist | 1. Check messages | Timestamps visible |
| APP-245 | Chat with special characters | Chat screen | 1. Send `@#$%^&*` | Characters preserved |
| APP-246 | Chat screen rotation preserves messages | Messages exist | 1. Rotate device | Messages preserved |
| APP-247 | Chat suggestion prompts (if present) | Empty chat | 1. Check UI | Quick suggestion chips |
| APP-248 | Chat copy message text | Message exists | 1. Long press message | Copy option shown |
| APP-249 | Chat share message | Message exists | 1. Long press 2. Share | Android share sheet |
| APP-250 | Chat back navigation | Chat screen | 1. Tap back | Returns to home |

---

## 11 · Pomodoro Timer (APP-251 → APP-275)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-251 | Pomodoro screen accessible | Home screen | 1. Navigate to Pomodoro | Timer UI displayed |
| APP-252 | Timer displays 25:00 by default | Pomodoro screen | 1. Check timer | "25:00" shown |
| APP-253 | Start button visible | Pomodoro screen | 1. Check buttons | Play/Start button present |
| APP-254 | Tap Start begins countdown | Pomodoro screen | 1. Tap Start | Timer counts down |
| APP-255 | Timer displays MM:SS format | Timer running | 1. Check display | "24:59" zero-padded |
| APP-256 | Pause button replaces Start when running | Timer running | 1. Check button | Pause icon shown |
| APP-257 | Tap Pause pauses timer | Timer running | 1. Tap Pause | Timer stops at current time |
| APP-258 | Resume timer after pause | Timer paused | 1. Tap Start/Resume | Timer continues |
| APP-259 | Reset button resets timer | Timer running/paused | 1. Tap Reset | Timer resets to 25:00 |
| APP-260 | Timer foreground notification | Timer running, app minimized | 1. Press home | Foreground notification visible |
| APP-261 | Timer continues in background | Timer running | 1. Press home 2. Wait 3. Return | Timer continued |
| APP-262 | Timer vibration on completion | Timer reaches 0 | 1. Wait for completion | Device vibrates |
| APP-263 | Timer sound on completion | Timer reaches 0 | 1. Wait for completion | Sound plays |
| APP-264 | Break mode after work session | Work session ends | 1. Complete 25 min | Break timer (5 min) starts |
| APP-265 | Long break after 4 sessions | 4 sessions done | 1. Complete 4th | 15 min break offered |
| APP-266 | Session counter displayed | Sessions completed | 1. Check counter | Session count shown |
| APP-267 | Timer screen rotation | Timer running | 1. Rotate device | Timer state preserved |
| APP-268 | Timer custom duration (if available) | Pomodoro screen | 1. Set custom time | Timer uses custom duration |
| APP-269 | Timer notification tap opens app | Notification shown | 1. Tap notification | App opens to timer screen |
| APP-270 | Timer stop from notification | Notification shown | 1. Tap stop on notification | Timer stops |
| APP-271 | Timer stats — total focus time | Sessions done | 1. Check stats | Total minutes displayed |
| APP-272 | Timer running prevents screen sleep | Timer active | 1. Wait without touching | Screen stays on |
| APP-273 | Timer with do-not-disturb mode | DND enabled | 1. Start timer | Timer works normally |
| APP-274 | Timer accessibility — TalkBack | Timer screen | 1. Enable TalkBack | Timer announced correctly |
| APP-275 | Timer performance — accurate countdown | Timer at 25:00 | 1. Wait 60 seconds | Timer shows 24:00 (±1 sec) |

---

## 12 · Study Planner & Calendar (APP-276 → APP-290)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-276 | Study planner screen accessible | Home screen | 1. Navigate to Planner | Calendar/planner displayed |
| APP-277 | Calendar displays current month | Planner screen | 1. Check calendar | Current month shown |
| APP-278 | Today highlighted on calendar | Planner screen | 1. Check today's date | Today highlighted |
| APP-279 | Navigate to next month | Planner screen | 1. Swipe/tap next | Next month shown |
| APP-280 | Navigate to previous month | Planner screen | 1. Tap previous | Previous month shown |
| APP-281 | Add study goal | Planner screen | 1. Tap add 2. Enter goal 3. Save | Goal appears in list |
| APP-282 | Study goal completion toggle | Goal exists | 1. Tap checkbox | Goal marked as complete |
| APP-283 | Delete study goal | Goal exists | 1. Long press 2. Delete | Goal removed |
| APP-284 | Reminder notification received | Reminder set | 1. Wait for reminder time | Push notification received |
| APP-285 | Calendar event date picker | Adding event | 1. Tap date | Date picker dialog opens |
| APP-286 | Calendar integration with device | Calendar permission | 1. Create study event | Event added to device calendar |
| APP-287 | Predict study time for note | Note exists | 1. Tap Predict Time | Estimated minutes displayed |
| APP-288 | Parse reminder from text | Input text | 1. Enter "Study math in 2 hours" | Parsed: topic="math", delay=120 |
| APP-289 | Planner landscape orientation | Landscape | 1. Rotate device | Calendar adapts |
| APP-290 | Planner empty state | No goals | 1. Check planner | "No study goals yet" message |

---

## 13 · Analytics Screen (APP-291 → APP-305)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-291 | Analytics screen accessible | Home screen | 1. Navigate to Analytics | Analytics screen opens |
| APP-292 | Study hours chart displayed | Usage data | 1. Check charts | Study hours chart visible |
| APP-293 | Notes created chart displayed | Notes exist | 1. Check charts | Notes over time chart |
| APP-294 | Quiz performance chart | Quizzes taken | 1. Check charts | Accuracy/score chart |
| APP-295 | Analytics empty state | New user | 1. View analytics | "Start studying" message |
| APP-296 | Analytics loading state | Opening analytics | 1. Navigate | Loading indicator |
| APP-297 | Total study time metric | Usage data | 1. Check summary | Total time displayed |
| APP-298 | Total notes count metric | Notes exist | 1. Check summary | Note count shown |
| APP-299 | Study streak display | Multiple days | 1. Check streak | Current streak shown |
| APP-300 | Analytics scrollable | Many metrics | 1. Scroll down | All content accessible |
| APP-301 | Analytics landscape mode | Landscape | 1. Rotate device | Charts readable |
| APP-302 | Analytics date range selector | Analytics screen | 1. Select date range | Charts update |
| APP-303 | Analytics screen rotation preserves | Data loaded | 1. Rotate | Data preserved |
| APP-304 | Analytics refresh | Analytics screen | 1. Pull to refresh | Data updated |
| APP-305 | Analytics performance — load time | Opening | 1. Measure load | Under 3 seconds |

---

## 14 · Profile Screen (APP-306 → APP-320)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-306 | Profile screen accessible | Home screen | 1. Tap Profile tab | Profile screen opens |
| APP-307 | User email displayed | Profile screen | 1. Check email | User email shown |
| APP-308 | User display name shown | Profile screen | 1. Check name | Name displayed |
| APP-309 | Edit display name | Profile screen | 1. Tap edit 2. Change name 3. Save | Name updated |
| APP-310 | Profile photo displayed (if set) | Profile screen | 1. Check avatar | Photo shown |
| APP-311 | Default avatar when no photo | No photo set | 1. Check avatar | Default icon displayed |
| APP-312 | Study stats on profile | Profile screen | 1. Check stats | Notes count, quiz score, streak visible |
| APP-313 | Logout button present | Profile screen | 1. Check buttons | Logout button visible |
| APP-314 | Tap Logout logs out | Profile screen | 1. Tap Logout 2. Confirm | Logged out, login screen shown |
| APP-315 | Logout confirmation dialog | Tapping logout | 1. Tap Logout | "Are you sure?" dialog |
| APP-316 | Cancel logout | Confirmation dialog | 1. Tap Cancel | Stays on profile |
| APP-317 | Profile scroll for content | Long profile | 1. Scroll | All content accessible |
| APP-318 | Profile landscape mode | Landscape | 1. Rotate | UI adapts |
| APP-319 | Profile level and XP displayed | Profile screen | 1. Check stats | Level and XP shown |
| APP-320 | Profile badges displayed | Badges earned | 1. Check badges | Badge icons/names shown |

---

## 15 · Settings Screen (APP-321 → APP-335)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-321 | Settings screen accessible | Profile/Home screen | 1. Navigate to Settings | Settings options rendered |
| APP-322 | Dark mode toggle | Settings screen | 1. Toggle dark mode | App theme changes |
| APP-323 | Notification toggle | Settings screen | 1. Toggle notifications | Preference saved |
| APP-324 | Change password option | Settings screen | 1. Tap Change Password | Password change form shown |
| APP-325 | Change password with valid inputs | Change password form | 1. Enter old + new password 2. Save | Password changed |
| APP-326 | Change password with wrong old password | Change password form | 1. Enter wrong old password | Error shown |
| APP-327 | Biometric lock toggle | Settings screen | 1. Toggle biometric | Biometric lock enabled/disabled |
| APP-328 | Delete account option | Settings screen | 1. Tap Delete Account 2. Confirm | Account deleted, returns to login |
| APP-329 | Delete account confirmation dialog | Tapping delete | 1. Tap Delete | Confirmation dialog shown |
| APP-330 | Cancel account deletion | Confirmation dialog | 1. Tap Cancel | Account not deleted |
| APP-331 | App version number displayed | Settings screen | 1. Scroll to bottom | Version number shown |
| APP-332 | About section | Settings screen | 1. Check about | App info displayed |
| APP-333 | Privacy policy link | Settings screen | 1. Tap Privacy Policy | Opens privacy page |
| APP-334 | Terms of service link | Settings screen | 1. Tap Terms | Opens terms page |
| APP-335 | Settings scroll for all options | Settings screen | 1. Scroll | All options accessible |

---

## 16 · Achievements & Gamification (APP-336 → APP-340)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-336 | Achievements screen accessible | Home screen | 1. Navigate to Achievements | Achievements screen opens |
| APP-337 | Badge list displayed | Badges exist | 1. Check list | Earned and locked badges shown |
| APP-338 | Badge detail on tap | Badge exists | 1. Tap badge | Badge details modal/screen |
| APP-339 | XP progress bar | Achievements screen | 1. Check progress | XP bar towards next level |
| APP-340 | Level displayed | Achievements screen | 1. Check level | Current level shown |

---

## 17 · Permissions & Security (APP-341 → APP-350)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| APP-341 | Camera permission requested for OCR | First OCR use | 1. Tap OCR/Camera | Camera permission dialog shown |
| APP-342 | Camera permission granted flow | Permission dialog | 1. Grant permission | Camera opens |
| APP-343 | Camera permission denied flow | Permission dialog | 1. Deny permission | Graceful error message |
| APP-344 | Storage permission for PDF import | First PDF import | 1. Tap import PDF | Storage permission dialog |
| APP-345 | Notification permission (Android 13+) | First notification | 1. App requests notification permission | POST_NOTIFICATIONS dialog shown |
| APP-346 | Microphone permission for voice | First voice input | 1. Tap mic | RECORD_AUDIO permission dialog |
| APP-347 | Calendar permission for planner | First calendar use | 1. Create calendar event | READ/WRITE_CALENDAR permission dialog |
| APP-348 | Foreground service notification for timer | Timer running | 1. Check notification | Foreground service notification present |
| APP-349 | App data cleared — returns to login | Settings → Clear data | 1. Clear app data 2. Relaunch | Login screen shown |
| APP-350 | Biometric authentication prompt on app reopen | Biometric enabled | 1. Close app 2. Reopen | Fingerprint/face prompt shown |

---

> **Total: 350 unique Appium (Android) test cases** across 17 categories
