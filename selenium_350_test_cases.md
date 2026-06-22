# SmartNotes AI — 350 Selenium (Web) Test Cases

> **Project:** [note-repo](https://github.com/Ujwal-code-otha/note-repo)
> **Platform:** Web (Next.js)
> **Automation Tool:** Selenium WebDriver
> **Total Test Cases:** 350

---

## Legend

| Column | Description |
|--------|-------------|
| **TC#** | Unique test-case ID (SEL-001 → SEL-350) |
| **Category** | Feature area under test |
| **Test Case Title** | Short, descriptive name |
| **Preconditions** | State required before execution |
| **Steps** | Numbered execution steps |
| **Expected Result** | Pass criteria |

---

## 1 · Login Page (SEL-001 → SEL-025)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| SEL-001 | Valid email + password login | Registered user | 1. Navigate to `/login` 2. Enter valid email 3. Enter valid password 4. Click "Sign In" | Redirected to `/dashboard` |
| SEL-002 | Login with incorrect password | Registered user | 1. Enter valid email 2. Enter wrong password 3. Click "Sign In" | Error: "Failed to sign in. Please check your credentials." |
| SEL-003 | Login with unregistered email | None | 1. Enter `nouser@test.com` 2. Enter any password 3. Click "Sign In" | Error message displayed |
| SEL-004 | Login with empty email field | None | 1. Leave email empty 2. Enter password 3. Click "Sign In" | HTML5 `required` validation blocks submit |
| SEL-005 | Login with empty password field | None | 1. Enter email 2. Leave password empty 3. Click "Sign In" | HTML5 `required` validation blocks submit |
| SEL-006 | Login with both fields empty | None | 1. Click "Sign In" directly | Form not submitted, validation shown |
| SEL-007 | Login with invalid email format | None | 1. Enter `notanemail` 2. Enter password 3. Click "Sign In" | Browser email validation error |
| SEL-008 | Email field type attribute is "email" | Login page | 1. Inspect email input | `type="email"` present |
| SEL-009 | Password field type attribute is "password" | Login page | 1. Inspect password input | `type="password"`, characters masked |
| SEL-010 | Loading spinner displayed during login | Registered user | 1. Enter valid credentials 2. Click "Sign In" | Spinner `animate-spin` visible during API call |
| SEL-011 | Sign In button disabled while loading | Login in progress | 1. Click "Sign In" 2. Observe button | Button has `disabled` attribute, opacity reduced |
| SEL-012 | Google Sign-In button visible | Login page | 1. Check for "Continue with Google" button | Button with Chrome icon present |
| SEL-013 | Google Sign-In triggers OAuth popup | Login page | 1. Click "Continue with Google" | Google OAuth redirect or popup initiated |
| SEL-014 | "Forgot Password?" link navigates | Login page | 1. Click "Forgot Password?" | Redirected to `/forgot-password` |
| SEL-015 | "Create one now" link navigates to register | Login page | 1. Click "Create one now" | Redirected to `/register` |
| SEL-016 | Error message clears on new attempt | Error shown | 1. Enter new credentials 2. Click "Sign In" | Previous error clears before new attempt |
| SEL-017 | Login with SQL injection in email | None | 1. Enter `' OR 1=1 --` in email 2. Submit | Error shown, no unauthorized access |
| SEL-018 | Login with XSS in email field | None | 1. Enter `<script>alert('xss')</script>` 2. Submit | Script not executed, input sanitized |
| SEL-019 | Login with XSS in password field | None | 1. Enter `<img onerror=alert(1) src=x>` in password 2. Submit | Script not executed |
| SEL-020 | Login page animates on load (Framer Motion) | None | 1. Navigate to `/login` | Form fades in with `opacity: 0 → 1, y: 20 → 0` |
| SEL-021 | Login page background glow on hover | Login page | 1. Hover over form container | Background blur elements brighten |
| SEL-022 | "Welcome Back" heading displayed | Login page | 1. Check h2 text | Contains "Welcome Back" |
| SEL-023 | Subtitle text displayed | Login page | 1. Check paragraph below heading | "Enter your credentials to access your notes" |
| SEL-024 | OR separator between form and Google | Login page | 1. Check layout | "OR" text between form and Google button |
| SEL-025 | Login persists session after page refresh | Logged in user | 1. Login 2. Refresh page | User remains on dashboard |

---

## 2 · Registration Page (SEL-026 → SEL-055)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| SEL-026 | Successful registration | None | 1. Navigate to `/register` 2. Fill name, email, password, confirm 3. Click "Create Account" | Redirected to `/dashboard` |
| SEL-027 | Registration with existing email | Registered user | 1. Enter existing email 2. Submit | Error: "Failed to create account" or Firebase error |
| SEL-028 | Registration with password mismatch | None | 1. Enter password "abc123" 2. Enter confirm "xyz456" 3. Submit | Error: "Passwords do not match" |
| SEL-029 | Registration with empty name | None | 1. Leave name empty 2. Fill other fields 3. Submit | HTML5 `required` blocks submit |
| SEL-030 | Registration with empty email | None | 1. Leave email empty 2. Fill other fields 3. Submit | Validation error |
| SEL-031 | Registration with empty password | None | 1. Leave password empty 2. Submit | Validation error |
| SEL-032 | Registration with empty confirm password | None | 1. Leave confirm password empty 2. Submit | Validation error |
| SEL-033 | Registration with weak password (< 6 chars) | None | 1. Enter password "12" 2. Submit | Firebase error about weak password |
| SEL-034 | Registration with invalid email format | None | 1. Enter "abc@" 2. Submit | Validation error |
| SEL-035 | Registration with very long name (200 chars) | None | 1. Enter 200-char name 2. Submit | Succeeds or shows max-length error |
| SEL-036 | Registration with special chars in name | None | 1. Enter `O'Brien-Smith` 2. Submit | Registration succeeds |
| SEL-037 | Registration with email containing + sign | None | 1. Enter `user+tag@gmail.com` 2. Submit | Registration succeeds |
| SEL-038 | Registration page heading displays | Register page | 1. Check h2 | "Join the Future" text shown |
| SEL-039 | Registration page subtitle displays | Register page | 1. Check subtitle | "Create your AI-powered second brain" |
| SEL-040 | Full Name input has User icon | Register page | 1. Check input area | User icon visible |
| SEL-041 | Email input has Mail icon | Register page | 1. Check input area | Mail icon visible |
| SEL-042 | Password inputs have Lock icons | Register page | 1. Check both password fields | Lock icons visible |
| SEL-043 | Google Sign-up button visible | Register page | 1. Check for Google button | "Sign up with Google" button present |
| SEL-044 | Google Sign-up triggers OAuth | Register page | 1. Click "Sign up with Google" | OAuth flow initiated |
| SEL-045 | "Log In" link navigates to login | Register page | 1. Click "Log In" | Redirected to `/login` |
| SEL-046 | Loading spinner on registration | Submitting | 1. Click "Create Account" | Spinner visible during API call |
| SEL-047 | Create Account button disabled during loading | Submitting | 1. Observe button state | `disabled` attribute applied |
| SEL-048 | Error message styling (red background) | Error present | 1. Trigger error | Red-bordered div with red text shown |
| SEL-049 | Registration with emoji in name | None | 1. Enter "John 🎓" as name 2. Submit | Handles gracefully |
| SEL-050 | Registration with Unicode name | None | 1. Enter "名前テスト" 2. Submit | Succeeds or graceful error |
| SEL-051 | Registration form clears error on new submit | Error shown | 1. Fix fields 2. Re-submit | Error clears before new attempt |
| SEL-052 | Registration page animation on load | None | 1. Navigate to `/register` | Form animates in with Framer Motion |
| SEL-053 | All four form fields are required | Register page | 1. Inspect all inputs | All have `required` attribute |
| SEL-054 | Password field masks input | Register page | 1. Type in password field | Characters masked (type="password") |
| SEL-055 | Confirm password field masks input | Register page | 1. Type in confirm password | Characters masked |

---

## 3 · Forgot Password (SEL-056 → SEL-070)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| SEL-056 | Forgot password page loads | None | 1. Navigate to `/forgot-password` | Reset form rendered |
| SEL-057 | Submit valid registered email | Registered email | 1. Enter email 2. Submit | Success: "Reset email sent" |
| SEL-058 | Submit unregistered email | None | 1. Enter unknown email 2. Submit | Error or generic success message |
| SEL-059 | Submit empty email | Forgot password page | 1. Leave empty 2. Submit | Validation prevents submission |
| SEL-060 | Submit invalid email format | Forgot password page | 1. Enter "notanemail" 2. Submit | Validation error |
| SEL-061 | Back to login navigation | Forgot password page | 1. Click login/back link | Redirected to `/login` |
| SEL-062 | Page heading renders correctly | Forgot password page | 1. Check heading | "Forgot Password" or similar heading |
| SEL-063 | Email input field has correct type | Forgot password page | 1. Inspect input | `type="email"` |
| SEL-064 | Submit button present and clickable | Forgot password page | 1. Check button | Submit/Reset button visible |
| SEL-065 | Loading state during submission | Forgot password page | 1. Submit email | Loading indicator shown |
| SEL-066 | Error styling matches design system | Error triggered | 1. Cause error | Red-bordered error box |
| SEL-067 | Success message styling | Success triggered | 1. Submit valid email | Green/success styled message |
| SEL-068 | Multiple rapid submissions | Forgot password page | 1. Click submit 5 times fast | Handled gracefully (no duplicates) |
| SEL-069 | Page responsive on mobile | Forgot password page | 1. Resize to 375px | Form usable, no overflow |
| SEL-070 | XSS in email field | Forgot password page | 1. Enter `<script>alert(1)</script>` | Not executed |

---

## 4 · Landing Page (SEL-071 → SEL-100)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| SEL-071 | Landing page loads without errors | None | 1. Navigate to `/` | All sections render |
| SEL-072 | Hero title "Your Second Brain" present | Landing page | 1. Check h1 | Text contains "Your Second Brain" |
| SEL-073 | Hero gradient text "Supercharged by AI" | Landing page | 1. Check span with gradient | Gradient text rendered |
| SEL-074 | Hero subtitle text present | Landing page | 1. Check paragraph | Contains "Transform your notes" |
| SEL-075 | "Get Started Free" button navigates to /register | Landing page | 1. Click "Get Started Free" | Redirected to `/register` |
| SEL-076 | "Get Started Free" has ArrowRight icon | Landing page | 1. Check button | ArrowRight icon present |
| SEL-077 | "See How it Works" scrolls to #features | Landing page | 1. Click "See How it Works" | Page scrolls to features section |
| SEL-078 | Features section h2 present | Landing page | 1. Scroll to features | "Unmatched AI Capabilities" heading |
| SEL-079 | Gradient divider below features heading | Landing page | 1. Check divider | 24px wide gradient bar visible |
| SEL-080 | Feature card 1: "AI Synthesis" displayed | Features section | 1. Check first card | Title "AI Synthesis" with Brain icon |
| SEL-081 | Feature card 2: "Smart Organization" displayed | Features section | 1. Check second card | Title "Smart Organization" with Cpu icon |
| SEL-082 | Feature card 3: "Instant Recall" displayed | Features section | 1. Check third card | Title "Instant Recall" with Zap icon |
| SEL-083 | Feature card hover moves card up | Features section | 1. Hover a card | Card animates `y: -10` |
| SEL-084 | Feature card icon background changes on hover | Features section | 1. Hover card | Icon bg brightens |
| SEL-085 | AI Section heading "AI Study Assistant" | AI section | 1. Scroll to AI section | Heading present |
| SEL-086 | AI Section terminal mockup displayed | AI section | 1. Check mockup | Terminal with dots, code text |
| SEL-087 | AI Section 4 bullet points present | AI section | 1. Count list items | "One-click summary", "Context-aware brainstorming", "Automated quiz creation", "Multi-language translation" |
| SEL-088 | AI Section CheckCircle2 icons visible | AI section | 1. Check list icons | Green check icons rendered |
| SEL-089 | Sync section heading "Real-time Cloud Sync" | Sync section | 1. Scroll to sync section | Heading present |
| SEL-090 | Sync section RefreshCw icon with animation | Sync section | 1. Check icon | Spinning icon present |
| SEL-091 | Sync stat "99.9%" Uptime | Sync section | 1. Check stats | "99.9%" with "Uptime" label |
| SEL-092 | Sync stat "<100ms" Latency | Sync section | 1. Check stats | "<100ms" with "Latency" label |
| SEL-093 | Sync stat "AES-256" Security | Sync section | 1. Check stats | "AES-256" with "Security" label |
| SEL-094 | Sync stat "Free" Cloud Storage | Sync section | 1. Check stats | "Free" with "Cloud Storage" label |
| SEL-095 | CTA "Ready to upgrade your mind?" heading | CTA section | 1. Scroll to CTA | Heading present |
| SEL-096 | CTA "Create Free Account" navigates to /register | CTA section | 1. Click button | Redirected to `/register` |
| SEL-097 | Footer "SmartNotes AI" branding displayed | Footer | 1. Scroll to footer | Logo with Sparkles icon |
| SEL-098 | Footer "Privacy Policy" link clickable | Footer | 1. Click "Privacy Policy" | Link functional |
| SEL-099 | Footer "Terms of Service" link clickable | Footer | 1. Click "Terms of Service" | Link functional |
| SEL-100 | Footer copyright "© 2024 SmartNotes AI" | Footer | 1. Check text | Copyright text present |

---

## 5 · Navbar (SEL-101 → SEL-130)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| SEL-101 | Navbar renders on all pages | Any page | 1. Check nav element | Fixed nav with glass styling |
| SEL-102 | Navbar logo links to `/` | Any page | 1. Click SmartNotes AI logo | Navigated to homepage |
| SEL-103 | Navbar logo has Sparkles icon | Any page | 1. Check logo area | Sparkles icon with cyan glow |
| SEL-104 | Navbar height is 80px (h-20) | Any page | 1. Measure nav height | 80px tall |
| SEL-105 | Navbar "Notes" link to /dashboard (logged in) | Logged in | 1. Click "Notes" | Navigated to `/dashboard` |
| SEL-106 | Navbar "Exams" link to /exams (logged in) | Logged in | 1. Click "Exams" | Navigated to `/exams` |
| SEL-107 | Navbar "Study" link to /study (logged in) | Logged in | 1. Click "Study" | Navigated to `/study` |
| SEL-108 | Navbar "Workspaces" link to /workspaces (logged in) | Logged in | 1. Click "Workspaces" | Navigated to `/workspaces` |
| SEL-109 | Navbar "Analytics" link to /analytics (logged in) | Logged in | 1. Click "Analytics" | Navigated to `/analytics` |
| SEL-110 | Navbar "Planner" link to /planner (logged in) | Logged in | 1. Click "Planner" | Navigated to `/planner` |
| SEL-111 | Active nav link highlighted | On `/dashboard` | 1. Check "Notes" link | Has `bg-white/5` and cyan color |
| SEL-112 | Streak counter (Flame icon + count) visible | Logged in | 1. Check streak area | Flame icon with number |
| SEL-113 | Level display (Zap icon + LVL) visible | Logged in | 1. Check level area | "LVL X" with Zap icon |
| SEL-114 | Settings gear icon links to /settings | Logged in | 1. Click Settings icon | Navigated to `/settings` |
| SEL-115 | User display name shown | Logged in | 1. Check profile area | User name displayed |
| SEL-116 | User badges displayed (max 2) | Logged in with badges | 1. Check profile | Up to 2 badge labels shown |
| SEL-117 | Profile photo displayed if available | User with photo | 1. Check avatar | Photo rendered in 40px circle |
| SEL-118 | Default avatar icon when no photo | User without photo | 1. Check avatar | UserIcon in circle shown |
| SEL-119 | "Entry" button shown for unauthenticated users | Not logged in | 1. Check navbar | "Entry" link to `/login` |
| SEL-120 | "Join Nexus" button shown for unauthenticated | Not logged in | 1. Check navbar | "Join Nexus" link to `/register` |
| SEL-121 | "Exam Arena" link visible for unauthenticated | Not logged in, on `/` | 1. Check navbar | "Exam Arena" link with Target icon |
| SEL-122 | Navbar hidden on auth pages (/login, /register) | Login page | 1. Check for nav links | Auth-specific nav links hidden |
| SEL-123 | Hamburger menu icon on mobile (<xl breakpoint) | Mobile viewport | 1. Resize to 768px | Menu (hamburger) icon visible |
| SEL-124 | Hamburger menu opens mobile nav | Mobile viewport | 1. Click Menu icon | Mobile menu slides down |
| SEL-125 | Mobile menu X button closes menu | Mobile menu open | 1. Click X icon | Menu closes with animation |
| SEL-126 | Mobile menu shows all nav links (logged in) | Mobile, logged in | 1. Open mobile menu | All 6 nav links visible |
| SEL-127 | Mobile menu "Terminate Session" logs out | Mobile, logged in | 1. Open menu 2. Click "Terminate Session" | User logged out |
| SEL-128 | Mobile menu links close menu on click | Mobile menu open | 1. Click a nav link | Menu closes, page navigates |
| SEL-129 | Mobile menu user level and XP shown | Mobile, logged in | 1. Open mobile menu | "LVL X • Y XP" displayed |
| SEL-130 | Mobile menu streak visible | Mobile, logged in | 1. Check mobile header | Flame icon + count visible |

---

## 6 · Dashboard (SEL-131 → SEL-150)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| SEL-131 | Dashboard loads after login | Logged in | 1. Navigate to `/dashboard` | Dashboard page renders |
| SEL-132 | Dashboard protected from unauthenticated access | Not logged in | 1. Navigate to `/dashboard` | Redirected to `/login` |
| SEL-133 | Dashboard displays note list component | Logged in | 1. View dashboard | NoteList component rendered |
| SEL-134 | Dashboard shows AI panel component | Logged in | 1. View dashboard | AIPanel component rendered |
| SEL-135 | Dashboard shows AI insights widget | Logged in | 1. Check sidebar/widget | AIInsights component visible |
| SEL-136 | Dashboard study calendar displayed | Logged in | 1. Check calendar area | StudyCalendar component rendered |
| SEL-137 | Dashboard quick actions visible | Logged in | 1. Check actions area | Add note, quiz, flashcard buttons |
| SEL-138 | Dashboard loads within 3 seconds | Logged in | 1. Measure page load | Loads < 3 seconds |
| SEL-139 | Dashboard responsive on tablet (768px) | Logged in | 1. Resize to 768px | Layout adjusts properly |
| SEL-140 | Dashboard responsive on mobile (375px) | Logged in | 1. Resize to 375px | Single-column layout |
| SEL-141 | Navigate to add note from dashboard | Logged in | 1. Click "Add Note" button | Note creation page/modal opens |
| SEL-142 | Recent notes section populated | User with notes | 1. View dashboard | Notes listed |
| SEL-143 | Empty state message for new users | New user, no notes | 1. View dashboard | "Create your first note" message |
| SEL-144 | Dashboard sidebar visible on desktop | Logged in, desktop | 1. Check left panel | Sidebar component rendered |
| SEL-145 | Dashboard logout button works | Logged in | 1. Click logout | Redirected to login page |
| SEL-146 | Dashboard page title tag set | Dashboard loaded | 1. Check document.title | Contains "Dashboard" or "SmartNotes" |
| SEL-147 | Dashboard search functionality | Notes exist | 1. Type in search | Notes filtered |
| SEL-148 | Note card click opens note detail | Notes exist | 1. Click on a note | Note detail/editor opens |
| SEL-149 | Dashboard delete note button | Note exists | 1. Click delete on note | Confirmation shown |
| SEL-150 | Dashboard refresh data | Dashboard loaded | 1. Trigger refresh | Data re-fetched |

---

## 7 · Notes CRUD & Editor (SEL-151 → SEL-185)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| SEL-151 | Create note with title and content | Logged in | 1. Click add note 2. Enter title 3. Enter content 4. Save | Note created, appears in list |
| SEL-152 | Create note with empty title | Logged in | 1. Leave title empty 2. Enter content 3. Save | Default title or validation error |
| SEL-153 | Create note with empty content | Logged in | 1. Enter title 2. Leave content empty 3. Save | Validation error or empty note saved |
| SEL-154 | Edit existing note title | Note exists | 1. Open note 2. Change title 3. Save | Title updated |
| SEL-155 | Edit existing note content | Note exists | 1. Open note 2. Modify content 3. Save | Content updated |
| SEL-156 | Delete note with confirmation | Note exists | 1. Click delete 2. Confirm | Note removed from list |
| SEL-157 | Cancel note deletion | Delete dialog shown | 1. Click cancel | Note not deleted |
| SEL-158 | Note list shows all user notes | Multiple notes | 1. View notes page | All notes listed |
| SEL-159 | Note list sorted by most recent | Multiple notes | 1. Check order | Newest first |
| SEL-160 | Search notes by title keyword | Notes exist | 1. Type keyword in search | Matching notes shown |
| SEL-161 | Search with no matching results | Notes exist | 1. Search "xyznonexistent" | "No notes found" message |
| SEL-162 | Note displays creation timestamp | Note exists | 1. View note | Timestamp visible |
| SEL-163 | Note with 10000 characters saved | Logged in | 1. Paste 10000 chars 2. Save | Note saved successfully |
| SEL-164 | Note with special characters `@#$%^&*` | Logged in | 1. Enter specials 2. Save | Saved and displayed correctly |
| SEL-165 | Note with emoji content 🎓📚 | Logged in | 1. Enter emojis 2. Save | Emojis preserved |
| SEL-166 | Note with multiline paragraphs | Logged in | 1. Enter paragraphs 2. Save | Line breaks preserved |
| SEL-167 | Note with code blocks | Logged in | 1. Enter code 2. Save | Code preserved in content |
| SEL-168 | Note with HTML tags in content | Logged in | 1. Enter `<b>bold</b>` 2. Save | Tags handled per editor |
| SEL-169 | Note list pagination/infinite scroll | 20+ notes | 1. Scroll down | More notes loaded |
| SEL-170 | Duplicate note titles allowed | Note exists | 1. Create another note with same title | Both notes exist |
| SEL-171 | Note auto-save via debounce (1 sec) | Editor open | 1. Type content 2. Wait 1 second | Content auto-saved |
| SEL-172 | Note share button copies URL | Note open | 1. Click Share | URL copied, "Copied" shown |
| SEL-173 | Note reminder button opens picker | Note editor | 1. Click Bell icon | Datetime picker opens |
| SEL-174 | Set reminder on a note | Note editor | 1. Click Bell 2. Select datetime | Reminder saved, "Active" label shown |
| SEL-175 | Clear reminder on a note | Reminder set | 1. Click Bell 2. Click "Clear Reminder" | Reminder removed |
| SEL-176 | Estimated study time calculated | Note with content | 1. Check toolbar | "Est. Study: X min" shown |
| SEL-177 | Estimated study time is 0 for empty note | Empty note | 1. Check toolbar | "Est. Study: 0 min" |
| SEL-178 | Font size zoom in button | Editor open | 1. Click ZoomIn | Font size increases by 2px |
| SEL-179 | Font size zoom out button | Editor open | 1. Click ZoomOut | Font size decreases by 2px |
| SEL-180 | Font size minimum is 12px | Editor open | 1. Click ZoomOut many times | Stops at 12px |
| SEL-181 | Font size maximum is 32px | Editor open | 1. Click ZoomIn many times | Stops at 32px |
| SEL-182 | Font size display shows current value | Editor open | 1. Check toolbar | "16px" (default) shown |
| SEL-183 | Note title placeholder "Note Title" | New note | 1. Check title input | Placeholder text visible |
| SEL-184 | Editor placeholder "Start writing your brilliance..." | New note | 1. Check editor area | Placeholder visible |
| SEL-185 | Note content supports rich text (bold, italic) | Editor open | 1. Use formatting | Text formatted via TipTap |

---

## 8 · AI Summarization (SEL-186 → SEL-205)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| SEL-186 | Summarize note with valid content | Note with 200+ chars | 1. Click "Summarize" | Summary bullet points displayed |
| SEL-187 | Summary loading indicator shown | Summarizing | 1. Click Summarize | Spinner/loading visible |
| SEL-188 | Summary error when API fails | API down | 1. Click Summarize | Error: "Neural synthesis failed" |
| SEL-189 | Summarize short content (<10 words) | Short note | 1. Click Summarize | Brief summary or "content too short" |
| SEL-190 | Summarize maximum content (10000 chars) | Long note | 1. Click Summarize | Summary generated successfully |
| SEL-191 | Summary output formatted as bullets | Summary generated | 1. Check format | Bullet points with heading |
| SEL-192 | Re-summarize same note | Previously summarized | 1. Click Summarize again | New summary generated |
| SEL-193 | Summary preserves original note | Note open | 1. Summarize 2. Check original | Note content unchanged |
| SEL-194 | Summarize non-English content | Spanish note | 1. Summarize | Summary generated |
| SEL-195 | Summarize note with code snippets | Code note | 1. Summarize | Code concepts summarized |
| SEL-196 | Summarize note with math content | Math note | 1. Summarize | Mathematical concepts extracted |
| SEL-197 | Summary word count is concise | Summary generated | 1. Count words | Under 500 words |
| SEL-198 | Summarize with HTML in content | `<b>text</b>` note | 1. Summarize | HTML stripped, summary works |
| SEL-199 | Summarize concurrent requests | Two notes | 1. Summarize both | Both return independently |
| SEL-200 | Summarize empty note | Empty note | 1. Click Summarize | Error or validation message |
| SEL-201 | Summary displayed in AI panel | Summary generated | 1. Check AI panel | Summary visible |
| SEL-202 | Summarize note with bullet list content | Note with bullets | 1. Summarize | Bullets condensed |
| SEL-203 | Summarize note with headings | Note with H1/H2 | 1. Summarize | Heading topics captured |
| SEL-204 | Summary copy to clipboard | Summary shown | 1. Select and copy | Text copyable |
| SEL-205 | Summarize timeout handling | Slow network | 1. Summarize on slow connection | Timeout error shown gracefully |

---

## 9 · AI Quiz Generation (SEL-206 → SEL-235)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| SEL-206 | Generate quiz from note content | Note with content | 1. Click "Generate Quiz" | 5 MCQ questions displayed |
| SEL-207 | Quiz displays question counter | Quiz active | 1. Check header | "Question 1 of 5" shown |
| SEL-208 | Quiz 30-second timer displayed | Quiz active | 1. Check timer | "30s" countdown visible |
| SEL-209 | Timer color turns red below 10 seconds | Timer < 10s | 1. Wait for timer | Timer border turns red |
| SEL-210 | Quiz progress bar renders | Quiz active | 1. Check progress bar | Gradient bar fills proportionally |
| SEL-211 | Select correct answer shows green | Quiz question | 1. Click correct option | Green border + CheckCircle2 icon |
| SEL-212 | Select wrong answer shows red | Quiz question | 1. Click wrong option | Red border + XCircle icon |
| SEL-213 | Correct answer highlighted after wrong selection | Wrong answer chosen | 1. Select wrong 2. Check | Correct answer also highlighted green |
| SEL-214 | Explanation panel shown after answering | Answer selected | 1. Select any answer | Explanation section animates in |
| SEL-215 | Explanation shows Award icon label | Explanation shown | 1. Check panel | "Explanation" label with Award icon |
| SEL-216 | "Next Question" button in explanation | Explanation shown | 1. Check button | "Next Question" with ArrowRight icon |
| SEL-217 | Click "Next Question" advances | On question 1 | 1. Answer 2. Click Next | Question 2 displayed |
| SEL-218 | Options disabled after answering | Answer selected | 1. Try clicking another option | All options disabled |
| SEL-219 | Timer resets to 30s on next question | Move to Q2 | 1. Answer Q1 2. Next | Timer resets to 30 |
| SEL-220 | Timer reaching 0 auto-advances | Timer at 0 | 1. Wait 30 seconds | Auto-advances to next question |
| SEL-221 | Quiz completion triggers onComplete | All answered | 1. Answer all 5 | onComplete callback fired with score, XP |
| SEL-222 | Score calculated correctly | Quiz completed | 1. Check score | Score = correct answers count |
| SEL-223 | XP earned = score × 100 | Quiz completed | 1. Check XP | XP = score * 100 |
| SEL-224 | Quiz results page displayed | Quiz finished | 1. Complete quiz | Results with score breakdown |
| SEL-225 | Quiz with boolean (True/False) questions | Quiz has boolean type | 1. Check question | "True" and "False" buttons shown |
| SEL-226 | Quiz with fill-in-the-blank | Quiz has fill type | 1. Check question | Text input with "Type your answer" placeholder |
| SEL-227 | Fill-in-blank Enter key submits answer | Fill question | 1. Type answer 2. Press Enter | Answer submitted |
| SEL-228 | Quiz loading indicator | Generating | 1. Click Generate Quiz | Spinner visible |
| SEL-229 | Quiz error handling | API down | 1. Generate quiz | Error: "Quiz generation failed" |
| SEL-230 | Generate quiz from empty note | Empty note | 1. Generate quiz | Error or empty quiz message |
| SEL-231 | Quiz question animation on transition | Switching questions | 1. Move to next | Slide animation (x: 20 → 0) |
| SEL-232 | Quiz question exit animation | Leaving question | 1. Click Next | Previous slides out (x: 0 → -20) |
| SEL-233 | Quiz title displayed | Quiz active | 1. Check h2 | Quiz title from content shown |
| SEL-234 | Quiz from very long note (10000 chars) | Long note | 1. Generate quiz | Quiz generated successfully |
| SEL-235 | Quiz keyboard navigation (Tab + Enter) | Quiz active | 1. Use Tab to focus option 2. Enter to select | Option selectable via keyboard |

---

## 10 · AI Flashcards (SEL-236 → SEL-260)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| SEL-236 | Generate flashcards from note | Note with content | 1. Click "Generate Flashcards" | Flashcard player with cards displayed |
| SEL-237 | Flashcard front shows "Question / Concept" label | Flashcard view | 1. Check front | Label text present |
| SEL-238 | Flashcard front shows card content | Flashcard view | 1. Check front | `card.front` text rendered |
| SEL-239 | "Click to reveal answer" hint on front | Flashcard view | 1. Check front | Hint text with Sparkles icon |
| SEL-240 | Click flashcard flips to back | Front showing | 1. Click card | Card rotates 180° to show back |
| SEL-241 | Back shows "Answer / Explanation" label | Back showing | 1. Check back | Purple label text |
| SEL-242 | Back shows answer content | Back showing | 1. Check back | `card.back` text rendered |
| SEL-243 | Click flipped card returns to front | Back showing | 1. Click card again | Card rotates back to front |
| SEL-244 | Flip animation uses spring physics | Flipping | 1. Click card | Smooth spring animation (stiffness: 260, damping: 20) |
| SEL-245 | "Study Mode" heading displayed | Flashcard player | 1. Check header | "Study Mode" with Brain icon |
| SEL-246 | "Flashcard session active" subtitle | Flashcard player | 1. Check subtitle | Subtitle text shown |
| SEL-247 | Card counter "1 / 5" displayed | First card | 1. Check counter | "1 / 5" format |
| SEL-248 | Progress bar fills proportionally | Card 3 of 5 | 1. Check bar | 60% filled |
| SEL-249 | Previous button disabled on first card | First card | 1. Check prev button | Disabled with low opacity |
| SEL-250 | Previous button enabled after first card | Card 2+ | 1. Check prev button | Enabled and clickable |
| SEL-251 | Click Previous goes to previous card | Card 2 | 1. Click prev | Card 1 shown |
| SEL-252 | Click Next goes to next card | Card 1 | 1. Click next | Card 2 shown |
| SEL-253 | Next resets flip state | Flipped card | 1. Click next | New card shows front |
| SEL-254 | "Flip" button in center toggles card | Any card | 1. Click "Flip" button | Card flips with RotateCcw icon |
| SEL-255 | Last card Next triggers onComplete | Last card | 1. Click next | Completion callback fired |
| SEL-256 | Card slide animation on navigation | Navigating | 1. Click next/prev | Cards slide left/right |
| SEL-257 | Flashcard loading indicator | Generating | 1. Click Generate | Spinner shown |
| SEL-258 | Flashcard error handling | API down | 1. Generate flashcards | Error: "Flashcard generation failed" |
| SEL-259 | Flashcard from empty note | Empty note | 1. Generate flashcards | Error/validation message |
| SEL-260 | Front card has cyan border, back has purple | Both sides | 1. Check borders | Cyan on front, purple on back |

---

## 11 · AI Chat Interface (SEL-261 → SEL-295)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| SEL-261 | Chat page loads at /chat | Logged in | 1. Navigate to `/chat` | Chat interface rendered |
| SEL-262 | Chat empty state displays welcome | No messages | 1. Check chat area | "How can I help you today?" heading |
| SEL-263 | Empty state subtitle text | No messages | 1. Check subtitle | "Start a conversation with your AI assistant" |
| SEL-264 | 4 suggestion buttons displayed | No messages | 1. Check suggestions | "Explain quantum physics", "Debug my React hook", "Summarize this PDF", "Quiz me on history" |
| SEL-265 | Clicking suggestion fills input | No messages | 1. Click suggestion | Input field populated with suggestion text |
| SEL-266 | Mode selector defaults to STUDY | Chat loaded | 1. Check mode | "Current Mode" shows STUDY with BookOpen icon |
| SEL-267 | Mode dropdown opens on click | Chat loaded | 1. Click mode selector | Dropdown with 4 modes shown |
| SEL-268 | Switch to CODING mode | Dropdown open | 1. Click CODING | Mode changes, Terminal icon shown |
| SEL-269 | Switch to RESEARCH mode | Dropdown open | 1. Click RESEARCH | Mode changes, Search icon shown |
| SEL-270 | Switch to EXAM mode | Dropdown open | 1. Click EXAM | Mode changes, GraduationCap icon shown |
| SEL-271 | Dropdown closes after selection | Mode selected | 1. Select mode | Dropdown closes |
| SEL-272 | ChevronDown rotates when dropdown open | Dropdown open | 1. Check icon | Rotated 180° |
| SEL-273 | "Powered by Gemini 1.5 Flash" badge visible | Desktop | 1. Check header | Badge with Sparkles icon |
| SEL-274 | Send message via send button | Chat open | 1. Type message 2. Click Send | Message sent, appears in chat |
| SEL-275 | Send message via Enter key | Chat open | 1. Type message 2. Press Enter | Message sent |
| SEL-276 | Shift+Enter creates new line (no send) | Chat open | 1. Type 2. Press Shift+Enter | New line in textarea, not sent |
| SEL-277 | Empty message not sent | Chat open | 1. Click Send with empty input | Nothing happens, submit prevented |
| SEL-278 | Send button disabled when input empty | Chat open | 1. Check send button | Has `cursor-not-allowed`, gray styling |
| SEL-279 | Send button enabled with text | Chat open | 1. Type text | Button turns cyan with shadow |
| SEL-280 | Send button shows loader when AI typing | Message sent | 1. After send | Loader2 spin icon on button |
| SEL-281 | Typing indicator (3 bouncing dots) | AI processing | 1. After sending | Three cyan dots bouncing |
| SEL-282 | AI response appears after processing | Message sent | 1. Wait for response | AI response bubble rendered |
| SEL-283 | Chat auto-scrolls to latest message | Multiple messages | 1. Send new message | Scrolled to bottom |
| SEL-284 | Paperclip attachment button present | Chat open | 1. Check input area | Paperclip icon button visible |
| SEL-285 | Microphone button present | Chat open | 1. Check input area | Mic icon button visible |
| SEL-286 | Disclaimer text at bottom | Chat open | 1. Check below input | "SmartNotes AI can make mistakes" |
| SEL-287 | Chat message with XSS payload | Chat open | 1. Send `<script>alert(1)</script>` | Script not executed |
| SEL-288 | Chat message with 5000 characters | Chat open | 1. Paste 5000 chars 2. Send | Message sent successfully |
| SEL-289 | Chat input is textarea (resizable) | Chat open | 1. Check input element | `<textarea>` with `rows="1"` |
| SEL-290 | Chat responsive on mobile (375px) | Chat on mobile | 1. Resize to 375px | Interface usable |
| SEL-291 | Gemini badge hidden on mobile | Mobile viewport | 1. Resize to 375px | Badge with `hidden md:flex` is hidden |
| SEL-292 | Chat input gradient border on focus | Chat open | 1. Focus on input | Gradient glow appears behind input |
| SEL-293 | Multiple rapid messages sent in order | Chat open | 1. Send 5 messages quickly | All appear in correct order |
| SEL-294 | Chat with emoji messages | Chat open | 1. Send 🤖📚 message | Emojis displayed correctly |
| SEL-295 | Send button disabled while AI is typing | AI processing | 1. Check send button | Disabled state applied |

---

## 12 · Pomodoro Timer (SEL-296 → SEL-315)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| SEL-296 | Pomodoro page loads at /study | Logged in | 1. Navigate to `/study` | Timer UI rendered |
| SEL-297 | Default mode is "Deep Work" (25:00) | Timer page | 1. Check timer | "25:00" displayed, work mode active |
| SEL-298 | "Deep Work" tab highlighted by default | Timer page | 1. Check tabs | "Deep Work" has white bg |
| SEL-299 | Click "Short Break" tab sets 5:00 | Timer page | 1. Click "Short Break" | Timer shows "05:00" |
| SEL-300 | Click "Long Break" tab sets 15:00 | Timer page | 1. Click "Long Break" | Timer shows "15:00" |
| SEL-301 | Play button starts timer | Timer at 25:00 | 1. Click Play | Timer starts counting down |
| SEL-302 | Timer displays MM:SS format with padding | Running | 1. Check display | "24:59" format (zero-padded) |
| SEL-303 | Colon blinks when timer active | Running | 1. Check colon | `animate-pulse` class applied |
| SEL-304 | "Neural Sync Active" status shown when running | Running | 1. Check status | Green dot + "Neural Sync Active" |
| SEL-305 | "Neural Sync Offline" shown when paused | Paused | 1. Check status | Red dot + "Neural Sync Offline" |
| SEL-306 | Pause button replaces Play when running | Running | 1. Check button | Pause icon shown |
| SEL-307 | Click Pause pauses timer | Running | 1. Click Pause | Timer stops |
| SEL-308 | Reset button resets to mode default | Timer at 15:00 | 1. Click Reset (RotateCcw) | Timer resets to 25:00 (work mode) |
| SEL-309 | Timer completion switches mode | Work session ends | 1. Wait for countdown | Switches to short break (5:00) |
| SEL-310 | After 4 sessions, long break offered | 4 sessions done | 1. Complete 4th session | Timer set to 15:00 long break |
| SEL-311 | Session counter increments | Complete session | 1. Check "Interval" stat | Shows `#2` after first session |
| SEL-312 | Protocol shows "Focus" or "Rest" | Timer page | 1. Check Protocol stat | "Focus" in work, "Rest" in break |
| SEL-313 | Nexus XP counter shows accumulated XP | Sessions done | 1. Check XP stat | Shows `+50` per session |
| SEL-314 | Circular SVG progress ring visible | Timer page | 1. Check SVG | Circle with gradient stroke |
| SEL-315 | Progress ring color changes per mode | Break mode | 1. Check ring | Cyan for work, purple for break |

---

## 13 · OCR Scanner (SEL-316 → SEL-330)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| SEL-316 | OCR scanner component renders | Logged in | 1. Navigate to documents/OCR | Upload and webcam options shown |
| SEL-317 | Drag-and-drop zone displayed | OCR page | 1. Check drop zone | "Drop Document" with Upload icon |
| SEL-318 | Supported formats text shown | OCR page | 1. Check drop zone | "PDF, PNG, JPG supported" |
| SEL-319 | Drag hover highlights drop zone | Dragging file | 1. Drag file over zone | Border turns cyan, bg changes |
| SEL-320 | Upload image extracts text | Image with text | 1. Upload image | Text extracted and passed to onExtract |
| SEL-321 | Upload PDF extracts text | PDF file | 1. Upload PDF | PDF text extracted |
| SEL-322 | Processing indicator during extraction | File uploaded | 1. Check UI | "Analyzing Document" with spinner |
| SEL-323 | Progress percentage shown | Processing | 1. Check progress | "OCR Engine is decoding content... X%" |
| SEL-324 | Progress bar fills during processing | Processing | 1. Check bar | Bar animates to progress percentage |
| SEL-325 | "Webcam Scan" button opens webcam | OCR page | 1. Click "Webcam Scan" | Webcam component renders |
| SEL-326 | Webcam capture button takes screenshot | Webcam open | 1. Click Camera capture button | Photo captured, OCR processing starts |
| SEL-327 | Webcam X button closes webcam | Webcam open | 1. Click X button | Returns to upload/webcam selection |
| SEL-328 | Error alert on extraction failure | OCR fails | 1. Upload corrupted file | Alert "Extraction failed. Please try again." |
| SEL-329 | Only single file accepted | OCR page | 1. Drop multiple files | Only first processed |
| SEL-330 | File type restriction enforced | OCR page | 1. Try uploading .exe | File rejected by dropzone |

---

## 14 · Collaboration Room (SEL-331 → SEL-350)

| TC# | Test Case Title | Preconditions | Steps | Expected Result |
|-----|----------------|---------------|-------|-----------------|
| SEL-331 | Collaboration room loads | Room ID, logged in | 1. Open collaboration room | Room UI with header, editor, chat sidebar |
| SEL-332 | Room name displayed in header | Room exists | 1. Check header | Room name or "Live Session" shown |
| SEL-333 | Green pulse dot indicates live session | Room open | 1. Check header | Green pulsing dot visible |
| SEL-334 | Presence avatars shown (up to 3) | Multiple users | 1. Check header | User initials in circles |
| SEL-335 | "+N" shown for excess collaborators | 4+ users | 1. Check header | "+1" overflow badge |
| SEL-336 | Notes/Quiz tab switcher works | Room open | 1. Click "Notes" or "Team Quiz" | View switches |
| SEL-337 | Editor renders in notes view | Notes tab active | 1. Check editor area | TipTap editor with content |
| SEL-338 | Edit shared note content | Notes view | 1. Type in editor | Content synced to room |
| SEL-339 | "Invite" button opens modal | Room open | 1. Click "Invite" | Modal with email input appears |
| SEL-340 | Invite modal email field required | Modal open | 1. Submit empty email | Validation error |
| SEL-341 | Invite sends successfully | Valid email | 1. Enter email 2. Submit | "Invitation sent successfully!" alert |
| SEL-342 | Close invite modal with X | Modal open | 1. Click X | Modal closes |
| SEL-343 | Chat sidebar visible | Room open | 1. Check right panel | "Communications" header with chat |
| SEL-344 | Send chat message | Room open | 1. Type in chat input 2. Submit | Message appears in chat |
| SEL-345 | Empty chat message not sent | Room open | 1. Submit empty chat | Nothing sent |
| SEL-346 | Chat auto-scrolls on new message | Multiple messages | 1. Send message | Chat scrolls to bottom |
| SEL-347 | "Awaiting Transmission..." empty state | No messages | 1. Check chat | Empty state message shown |
| SEL-348 | Generate Team Quiz button | Quiz tab, notes exist | 1. Click "Generate Team Quiz" | Quiz generated from shared notes |
| SEL-349 | "No Quiz Active" state displayed | Quiz tab, no quiz | 1. Click Quiz tab | Trophy icon + "No Quiz Active" |
| SEL-350 | Leave room via LogOut button | Room open | 1. Click leave button | onLeave callback fired |

---

> **Total: 350 unique Selenium (Web) test cases** across 14 categories
