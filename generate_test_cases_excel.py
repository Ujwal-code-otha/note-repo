import os
import re
import openpyxl

def parse_markdown_to_excel(md_file_path, excel_file_path):
    if not os.path.exists(md_file_path):
        print(f"File not found: {md_file_path}")
        return

    wb = openpyxl.Workbook()
    ws = wb.active
    ws.title = "Test Cases"

    # Try to parse as table first
    has_table = False
    with open(md_file_path, 'r', encoding='utf-8') as f:
        for line in f:
            line = line.strip()
            if line.startswith('|') and line.endswith('|'):
                if not re.match(r'^\|[-\s\|]+\|$', line):
                    has_table = True
                    break

    if has_table:
        with open(md_file_path, 'r', encoding='utf-8') as f:
            for line in f:
                line = line.strip()
                if line.startswith('|') and line.endswith('|'):
                    if re.match(r'^\|[-\s\|]+\|$', line):
                        continue
                    columns = [col.strip() for col in line.split('|')[1:-1]]
                    if columns and columns[0] == 'Column' and columns[1] == 'Description':
                        continue
                    ws.append(columns)
    else:
        # Parse list format (e.g. load_test_350_test_cases.md)
        ws.append(["TC#", "Test Case Title", "Category", "Scenario", "Expected Result"])
        with open(md_file_path, 'r', encoding='utf-8') as f:
            content = f.read()
        
        # Split by ### TC-
        blocks = content.split('### TC-')
        for i in range(1, len(blocks)):
            block = blocks[i]
            lines = block.split('\n')
            header_line = lines[0].strip()
            match = re.match(r'^(\d+):\s*(.*)$', header_line)
            if not match:
                continue
            tc_id = f"TC-{match.group(1)}"
            title = match.group(2)
            
            category = ''
            scenario = ''
            expected = ''
            
            for line in lines:
                line = line.strip()
                if line.startswith('- **Category**:'):
                    category = line.replace('- **Category**:', '').strip()
                elif line.startswith('- **Scenario**:'):
                    scenario = line.replace('- **Scenario**:', '').strip()
                elif line.startswith('- **Expected Result**:'):
                    expected = line.replace('- **Expected Result**:', '').strip()
            
            ws.append([tc_id, title, category, scenario, expected])

    wb.save(excel_file_path)
    print(f"Generated {excel_file_path}")

def main():
    base_dir = os.path.dirname(os.path.abspath(__file__))
    files = [
        ("appium_350_test_cases.md", "appium_350_test_cases.xlsx"),
        ("selenium_350_test_cases.md", "selenium_350_test_cases.xlsx"),
        ("load_test_350_test_cases.md", "load_test_350_test_cases.xlsx")
    ]
    
    for md_file, excel_file in files:
        md_path = os.path.join(base_dir, md_file)
        excel_path = os.path.join(base_dir, excel_file)
        parse_markdown_to_excel(md_path, excel_path)

if __name__ == "__main__":
    main()
