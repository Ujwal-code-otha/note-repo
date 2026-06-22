import os
import re
import openpyxl

def parse_markdown_table_to_excel(md_file_path, excel_file_path):
    if not os.path.exists(md_file_path):
        print(f"File not found: {md_file_path}")
        return

    wb = openpyxl.Workbook()
    ws = wb.active
    ws.title = "Test Cases"

    with open(md_file_path, 'r', encoding='utf-8') as f:
        for line in f:
            line = line.strip()
            # Match table rows, ignoring the legend or header separators like |---|
            if line.startswith('|') and line.endswith('|'):
                # Exclude the separator lines
                if re.match(r'^\|[-\s\|]+\|$', line):
                    continue
                
                # Split by | and strip whitespace from each cell
                columns = [col.strip() for col in line.split('|')[1:-1]]
                
                # Exclude Legend rows
                if columns and columns[0] == 'Column' and columns[1] == 'Description':
                    continue
                    
                ws.append(columns)

    if ws.max_row == 1 and not ws.cell(row=1, column=1).value:
        print(f"No table data found in {md_file_path}")
        return

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
        parse_markdown_table_to_excel(md_path, excel_path)

if __name__ == "__main__":
    main()
